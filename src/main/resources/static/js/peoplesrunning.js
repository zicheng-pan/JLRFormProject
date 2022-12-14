let gui, canvas, c, width, height, id, fks, runners;

let cdsids = [];
let runnercache = {};
let runnershoulddelete = [];
let contentMap = {};
let contentlengthMap = {};
let contentTemplate = ["∧,,∧", "∩ ∩", "(╥﹏╥)", "(⊙﹏⊙)", "/≥﹏≤\\", "(o'.'o)", "(-'.'-)", "happily running ", "mission completed", "距离成功只差一步", "很开心", "今日必得大奖", "好运连连", "is running on success"
];
let contentLength = [4, 6, 6, 6, 8, 8, 10, 12, 10, 16, 8, 14, 8, 12];


function random(min, max) {

    return Math.floor(Math.random() * (max - min)) + min;

}

class Arm {
    constructor() {
        this.x = 0;
        this.y = 0;
        this.angle = 0;
        this.scale = 0;
        this.length = 0;
        this.centerAngle = 0;
        this.rotationRange = Math.PI / 2;
        this.parent = null;
        this.phaseOffset = 0;
        this.cdsid = "";
    }

    create(length, centerAngle, rotationRange, phaseOffset, scale) {
        const obj = new this.constructor();
        obj.initialize(length, centerAngle, rotationRange, phaseOffset, scale);

        return obj;
    }

    initialize(length, centerAngle, rotationRange, phaseOffset, scale) {
        this.length = length;
        this.centerAngle = centerAngle;
        this.rotationRange = rotationRange;
        this.phaseOffset = phaseOffset;
        this.scale = scale;
    }

    setPhase(phase) {
        this.angle = this.centerAngle + Math.sin(phase + this.phaseOffset) * this.rotationRange;
    }

    getEndX() {
        let angle = this.angle;
        let parent = this.parent;

        while (parent) {
            angle += parent.angle;
            parent = parent.parent;
        }

        return Math.cos(angle) * this.length + this.x;
    }

    getEndY() {
        let angle = this.angle;
        let parent = this.parent;

        while (parent) {
            angle += parent.angle;
            parent = parent.parent;
        }

        return Math.sin(angle) * this.length + this.y;
    }

    render(c, i, t) {
        this.scale = Math.abs(this.y / window.innerHeight);

        this.y = Math.sin(this.x / width * Math.PI + t) * 20 * this.scale + this.y;

        c.save();
        c.lineCap = 'round';
        c.lineWidth = 30 * this.scale;


        if (this.parent === null && i === 0) {
            // body
            c.beginPath();
            c.moveTo(this.x, this.y);
            c.lineTo(this.x, this.y - this.length);
            c.stroke();
            // head
            c.beginPath();
            c.arc(this.x, this.y - this.length, gui.params.maxSize * this.scale, 0, Math.PI * 2, false);
            c.fill();
            // shadow
            c.save();
            c.globalAlpha = 0.03;
            c.beginPath();
            c.ellipse(this.x, this.y + this.length * 2, this.length * 3 * this.scale, this.length / 2 * this.scale, 0, Math.PI * 2, false);
            c.fill();
            c.restore();
            let color = random(120, 240);
            // c.fillStyle = 'rgb(' + color + ',' +
            //     22 + ',' + (255-color) + ')';
            c.fillStyle = 'rgb(0,0,0)';
            let fontheight = Math.pow(Math.log(this.y), 2);
            c.font = fontheight + "px serif";
            // console.log("#####");
            // console.log(this.cdsid);
            let content = this.cdsid;
            if (contentMap.hasOwnProperty(this.cdsid)) {
                content = contentMap[this.cdsid];
            } else {
                let rande = random(0, contentTemplate.length);
                let randomData = contentTemplate[rande];
                content = content + "  " + randomData;
                let contentline = "";
                for (let j = 0; j < content.length + contentLength[rande]; j++) {
                    contentline = contentline + "-";
                }
                contentlengthMap[this.cdsid] = contentline;
                contentMap[this.cdsid] = content;
            }

            let x_position = this.x + 50 * this.scale;

            c.fillText(contentlengthMap[this.cdsid], x_position, this.y - fontheight)
            c.fillText(" " + content , x_position, this.y);
            c.fillText(contentlengthMap[this.cdsid], x_position, this.y + fontheight)

        }

        c.beginPath();
        c.moveTo(this.x, this.y);
        c.lineTo(this.getEndX(), this.getEndY());
        c.stroke();
        c.restore();
    }
}

class FKSystem {
    constructor(x, y, v, rand, cdsid) {
        this.initialize(x, y, v, rand, cdsid);
    }

    initialize(x, y, v, rand, cdsid) {
        this.x = x;
        this.y = y;
        this.v = v;
        this.lastArm = null;
        this.phase = 0;
        this.speed = 0.2 * rand + 0.1;
        this.arms = [];
        this.cdsid = cdsid;
    }

    addArm(length, centerAngle, rotationRange, phaseOffset, scale, cdsid) {
        const a = new Arm();
        const arm = a.create(length, centerAngle, rotationRange, phaseOffset, scale);
        // console.log(index)
        arm.cdsid = cdsid;
        // console.log(cdsids[index])
        // console.log(this.cdsid)
        this.arms.push(arm);

        if (this.lastArm) {
            arm.parent = this.lastArm;
        }

        this.lastArm = arm;
        this.update();
    }

    update() {
        for (let i = 0; i < this.arms.length; i++) {
            const arm = this.arms[i];

            arm.setPhase(this.phase);

            if (arm.parent) {
                arm.x = arm.parent.getEndX();
                arm.y = arm.parent.getEndY();
            } else {
                arm.x = this.x;
                arm.y = this.y;
            }
        }

        this.phase += this.speed;
    }

    updatePosition() {
        var flag = false;
        this.x += this.v;
        if (this.x > window.innerWidth + 10) {


            var index = runnershoulddelete.indexOf(this.cdsid);
            if (index >= 0) {
                runnershoulddelete.splice(index, 1);
                removeArrayElement(cdsids, this.cdsid);
                delete runnercache[this.cdsid];
                flag = true;
            } else {
                this.x = 0 - 10;
            }
        }

        if (this.x < 0 - 10) {

            var index = runnershoulddelete.indexOf(this.cdsid);
            if (index >= 0) {
                runnershoulddelete.splice(index, 1);
                removeArrayElement(cdsids, this.cdsid);
                delete runnercache[this.cdsid];
                flag = true;
            } else {
                this.x = window.innerWidth + 10;
            }
        }
        return flag;
    }

    render(c, t) {
        for (let i = 0; i < this.arms.length; i++) {

            this.arms[i].render(c, i, t);
        }
    }

    rotateArm(index, angle) {
        this.arms[index].angle = angle;
    }
}

const removeArrayElement = (array, e) => {
    var index = array.indexOf(e);
    if (index >= 0) {
        array.splice(index, 1);
    }
}

const setupGui = () => {
    gui = new dat.GUI();

    gui.params = {
        timeScale: 0.01,
        // number: window.innerWidth < 500 ? 50 : 80,
        number: cdsids.length,
        maxSize: 50,

        reset: () => initialize()
    };


    // gui.ctrls = {
    //     // timeScale: gui.add(gui.params, 'timeScale', 0.001, 0.01, 0.001),
    //     // number: gui.add(gui.params, 'number', 1, 500, 1)
    //     //     .onChange(() => initialize()),
    //     // maxSize: gui.add(gui.params, 'maxSize', 50, 200, 1)
    //     //     .onChange(() => initialize()),
    //     // reset: gui.add(gui.params, 'reset')
    // };
};

const setupCanvas = () => {
    canvas = document.createElement('canvas');
    document.getElementsByTagName('body')[0].appendChild(canvas);
    c = canvas.getContext('2d');
};


const drawwithcatch = (drawerids, runners) => {

    width = canvas.width = window.innerWidth;
    height = canvas.height = window.innerHeight;

    for (let i = 0; i < drawerids.length; i++) {

        if (runnercache.hasOwnProperty(drawerids[i])) {
            runners.push(runnercache[drawerids[i]]);
        } else {
            const x = width * Math.random();
            const y = Math.max(height * Math.random() * Math.random(), 50);
            console.log(x)
            console.log(y)
            const rand = Math.max(Math.random(), 0.3);

            const s = y / height;

            let v = 10;

            if (Math.random() < 0.5) {
                v *= -1;
            }

            const right = new FKSystem(x, y, v * s, rand * s, drawerids[i]);
            const left = new FKSystem(x, y, v * s, rand * s, drawerids[i]);

            left.phase = Math.PI;

            if (v < 0) {
                // length, center angle, rotation angle, phase offset, scale
                right.addArm(gui.params.maxSize * s, Math.PI / 2, Math.PI / 4, 0, s, drawerids[i]);
                right.addArm(gui.params.maxSize * s, -0.87, 0.87, -1.5, s, drawerids[i]);

                left.addArm(gui.params.maxSize * s, Math.PI / 2, Math.PI / 4, 0, s, drawerids[i]);
                left.addArm(gui.params.maxSize * s, -0.87, 0.87, -1.5, s, drawerids[i]);
            } else {
                right.addArm(gui.params.maxSize * s, Math.PI / 2, Math.PI / 4, 0, s, drawerids[i]);
                right.addArm(gui.params.maxSize * s, 0.87, 0.87, -1.5, s, drawerids[i]);

                left.addArm(gui.params.maxSize * s, Math.PI / 2, Math.PI / 4, 0, s, drawerids[i]);
                left.addArm(gui.params.maxSize * s, 0.87, 0.87, -1.5, s, drawerids[i]);
            }
            var temp = [right, left];
            runners.push(temp);
            runnercache[drawerids[i]] = temp;
        }
    }
};

const initialize = () => {
    if (id) {
        cancelAnimationFrame(id);
    }
    runners = [];
    drawwithcatch(cdsids, runners);
    drawwithcatch(runnershoulddelete, runners);

    draw(0);
};


const draw = (t) => {
    t *= gui.params.timeScale;

    c.save();
    c.clearRect(0, 0, width, height);

    var flag = false;
    for (let j = 0; j < runners.length; j++) {
        for (let i = 0; i < runners[j].length; i++) {
            runners[j][i].render(c, t);

            runners[j][i].update();
            flag = flag || runners[j][i].updatePosition();
        }
    }

    c.restore();

    id = requestAnimationFrame(draw);
    if (flag) {
        initialize();
    }
};

function connect() {

    var socket = new SockJS('/endpoint-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/peoplesrunning', function (result) {
            if (cdsids.indexOf(result.body) == -1) {
                if (cdsids.length >= 25) {
                    for (let i = 0; i < 5 && i < runnershoulddelete.length; i++) {
                        var id = runnershoulddelete.shift();
                        delete runnercache[id];
                    }
                }
                if (cdsids.length >= 15) {
                    for (let i = 0; i < 10; i++) {
                        var id = cdsids.shift();
                        // delete runnercache[id];
                        runnershoulddelete.push(id);
                    }
                }
                cdsids.push(result.body);
                initialize();
            }
        });
    });
}

connect();
// setInterval(function (){
//     cdsids.pop(1)
// },8000);
(() => {
    window.addEventListener('DOMContentLoaded', () => {
        console.clear();

        setupGui();
        setupCanvas();

        initialize();
    });

    window.addEventListener('resize', () => {
        initialize();
    });


})();
