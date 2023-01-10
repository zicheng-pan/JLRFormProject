new Vue({
    el: '#app',
    template: `
    <div class="lucky-draw-view">
      <!-- 抽奖显示页面 -->
      <div :class="[isLuckyDraw ? 'lucky-draw-content lucky-draw-start' : 'lucky-draw-content', users.length<=5 ? 'luck-draw-content-center':'']">
      
        <div :class="isLuckyDraw ? 'lucky-draw-users lucky-draw-users-start' : 'lucky-draw-users'">
          <div class="lucky-draw-user" v-for="item in users.slice(0,5)" :key="index">
            <div class="lucky-draw-user-name">{{ item.ename }}</div>
            <div class="lucky-draw-user-department">{{ item.cname }}</div>
            <div class="lucky-draw-user-department">{{ item.cdsid }}</div>
          </div>
          <div v-if="!users.length && !surplusUsers.length" class="ucky-draw-empty">Still no finished yet</div>
        </div>  
        
        <div v-if="users.length>5" :class="isLuckyDraw ? 'lucky-draw-users lucky-draw-users-start' : 'lucky-draw-users'" >
          <div class="lucky-draw-user" v-for="item in users.slice(5,10)" :key="index">
            <div class="lucky-draw-user-name">{{ item.ename }}</div>
            <div class="lucky-draw-user-department">{{ item.cname }}</div>
            <div class="lucky-draw-user-department">{{ item.cdsid }}</div>
          </div>
          <div v-if="!users.length && !surplusUsers.length" class="ucky-draw-empty">Still no finished yet</div>
        </div>  
        
        <div v-if="users.length>10" :class="isLuckyDraw ? 'lucky-draw-users lucky-draw-users-start' : 'lucky-draw-users'">
          <div class="lucky-draw-user" v-for="item in users.slice(10,15)" :key="index">
            <div class="lucky-draw-user-name">{{ item.ename }}</div>
            <div class="lucky-draw-user-department">{{ item.cname }}</div>
            <div class="lucky-draw-user-department">{{ item.cdsid }}</div>
          </div>
          <div v-if="!users.length && !surplusUsers.length" class="ucky-draw-empty">Still no finished yet</div>
        </div>  
      </div>
      <!-- 设置奖项，人数，并开始抽奖 -->
      <div class="lucky-draw-tool-left">
  
        <a-button @click="yidengjiang">
          First Price
        </a-button>
        <!-- 抽奖按钮 -->
        <a-button @click="erdengjiang">
          Second Price
        </a-button>
        <a-button @click="quxiao">
          Cancel
        </a-button>
        <a-button @click="finalthank">
          Final Thank
        </a-button>
      </div>
    </div>
  `,
    data() {
        return {
            // 当前第几轮抽奖
            number: 1,
            tempNumber: 0,
            // 抽奖人数
            numberPeople: undefined,
            // 抽奖状态
            isLuckyDraw: false,
            // 滚动名单
            users: [],
            lastUsers: [],
            // 0 默认抽奖模式，1 自定义抽奖模式
            modeType: 0,
            // 自定义奖项列表
            customs: [],
            // 当前选中奖项
            custom: undefined,
            // 中奖人员
            winningUsers: [],
            // 剩余未中奖人数
            surplusUsers: [],
            // 滚动定时器
            luckyDrawTime: undefined,
            user_cache: []
        }
    },
    mounted() {
        // 获取模式
        const modeType = localStorage.getItem('modeType')
        if (modeType) {
            this.modeType = Number(modeType)
        } else {
            this.modeType = 0
        }
        // 获取自定义列表
        this.customs = JSON.parse(localStorage.getItem('customs')) || []
        // 剩余未中奖人数
        this.surplusUsers = [...users]
        // 获取中奖用户
        this.winningUsers = JSON.parse(localStorage.getItem('winning-users')) || []
        // 初始化轮数
        this.tempNumber = this.winningUsers.length
        this.number = this.tempNumber + 1
        // 清理中奖用户
        this.winningUsers.forEach(item => {
            // 解析ids
            const ids = item.ids.split('、').map(Number)
            // 移除中奖id
            ids.forEach(id => {
                // 定位中奖用户
                const index = this.surplusUsers.findIndex(user => user.id === id)
                // 从剩余抽奖用户名单中移除
                if (index !== -1) {
                    this.surplusUsers.splice(index, 1)
                }
            })
        })

        // setInterval(this.getUsers(this.users), 1000);
    },
    methods: {
        // 切换奖项
        handleModeTypeChange(value, e) {
            // 记录奖项
            this.custom = e.data.attrs.item
        },
        yidengjiang() {
            startAnimate();
            this.isLuckyDraw = false
            this.numberPeople = undefined
            this.number += 1
            users = this.users;
            for (var i = 0; i < this.user_cache.length; i++) {
                users.pop();
            }
            this.startLuckyDraw("FIRST");
            var audioDOM = new Audio("../mp3/banjiang.mp3");
            audioDOM.play();
            // this.stopLuckyDraw()

        },
        erdengjiang() {
            startAnimate();
            this.isLuckyDraw = false
            this.numberPeople = undefined
            this.number += 1
            users = this.users;
            for (var i = 0; i < this.user_cache.length; i++) {
                users.pop();
            }
            this.startLuckyDraw("SECOND");
            var audioDOM = new Audio("../mp3/banjiang.mp3");
            audioDOM.play();
        },
        quxiao() {
            startAnimate();
            this.isLuckyDraw = false
            this.numberPeople = undefined
            this.number += 1
            users = this.users;
            for (var i = 0; i < this.user_cache.length; i++) {
                users.pop();
            }
        },

        finalthank() {
            window.location.href = 'finalThank.html'
        },

        startLuckyDraw(prize) {
            if (this.tempNumber != this.number) {
                this.tempNumber = this.number
                stopAnimate('sphere')
                // setTimeout(() => {
                //     this.getUpdateUsers();
                //   // this.GetUsers()
                // }, 2000);
                this.isLuckyDraw = true
                this.infiniteCycle(prize)
            }
        },
        // 停止抽奖
        stopLuckyDraw() {
            if (this.tempNumber === this.number) {
                if (this.luckyDrawTime) {
                    clearInterval(this.luckyDrawTime)
                    this.luckyDrawTime = undefined
                    this.users = this.lastUsers
                    // this.saveWinningUsers()
                } else {
                    this.isLuckyDraw = false
                    this.numberPeople = undefined
                    this.number += 1
                    stopAnimate('grid')
                }
            }
        },
        // 循环名单
        infiniteCycle(prize) {
            if (this.luckyDrawTime) {
                clearInterval(this.luckyDrawTime)
                this.luckyDrawTime = undefined
            }
            // users = [];
            users = this.users;
            user_cache = this.user_cache;
            $.ajax({
                url: serverhost + '/user/priceRank',
                type: 'get',
                dataType: 'json',
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        console.log(data[i])
                        console.log(data[i].priceLevel)
                        console.log(prize)
                        if (data[i].priceLevel == prize) {
                            console.log("matched " + prize)
                            console.log("data:" + data[i])
                            users.push(data[i])
                            user_cache.push(data[i])
                        }
                    }
                },
                error: function (xhr, errorType, error) {
                    alert('Ajax request error, errorType: ' + errorType + ', error: ' + error)
                }
            });

        },
        // 更新抽奖名单
        updateNumberUsers() {
            const tempUsers = []
            var number = 0;
            const total = users.length
            while (number < this.numberPeople) {
                const index = parseInt(Math.random() * total)
                const user = users[index]
                if (user) {
                    tempUsers.push(user)
                }
                number++;
            }
            // this.users = tempUsers
        },
        GetUsers() {
            // 剩余用户
            const surplusUsers = [...this.surplusUsers]
            const lastUsers = []
            // 标记用户
            surplusUsers.forEach(user => {
                // 编号有值
                if (user.number > 0) {
                    if (this.modeType == 0) { // 默认抽奖模式
                        if (user.number == this.number) {
                            if (lastUsers.length < this.numberPeople) {
                                lastUsers.push(user)
                                const index = this.surplusUsers.indexOf(user)
                                if (index !== -1) {
                                    this.surplusUsers.splice(index, 1)
                                }
                            }
                        }
                    } else if (this.modeType == 1) { // 自定义奖项模式
                        if (user.number == this.custom.tag && this.custom.tag != 0) {
                            if (lastUsers.length < this.numberPeople) {
                                lastUsers.push(user)
                                const index = this.surplusUsers.indexOf(user)
                                if (index !== -1) {
                                    this.surplusUsers.splice(index, 1)
                                }
                            }
                        }
                    } else {
                    }
                }
            })
            // 随机用户
            while (this.surplusUsers.length > 0 && lastUsers.length < this.numberPeople) {
                const index = parseInt(Math.random() * this.surplusUsers.length)
                const user = this.surplusUsers[index]
                if (user) {
                    const index = this.surplusUsers.indexOf(user)
                    if (index !== -1) {
                        lastUsers.push(user)
                        this.surplusUsers.splice(index, 1)
                    }
                }
            }
            // 打乱顺序
            var length = lastUsers.length
            if (length > 1) {
                for (var i = 0; i < length - 1; i++) {
                    var index = parseInt(Math.random() * (length - i));
                    var temp = lastUsers[index];
                    lastUsers[index] = lastUsers[length - i - 1];
                    lastUsers[length - i - 1] = temp;
                }
            }
            // 记录数据
            this.lastUsers = lastUsers
        }
    }
})