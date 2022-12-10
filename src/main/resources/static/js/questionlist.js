$.fn.answerSheet = function (b) {
    var a = {mold: "card",};
    var c = $.extend({}, a, b);
    return $(this).each(function () {
        var i = $(this).find(".card_cont");
        var g = i.length, d = g - 1, f = g - 1, e = ".card_cont";
        for (var h = 1; h <= g; h++) {
            i.eq(d).css({"z-index": h});
            d -= 1
        }
        $(this).show();
        if (c.mold == "card") {
            i.find("ul li label").click(function () {
                var l = $(this).parents(e).index(), k = i, j = $(this).parents(e);
                if (l == f) {
                    return
                } else {
                    setTimeout(function () {
                        j.addClass("cardn");
                        setTimeout(function () {
                            k.eq(l + 3).addClass("card3");
                            k.eq(l + 2).removeClass("card3").addClass("card2");
                            k.eq(l + 1).removeClass("card2").addClass("card1");
                            j.removeClass("card1")
                        }, 200)
                    }, 100)
                }
            });
            $(".card_bottom").find(".prev").click(function () {
                var k = $(this).parents(e).index(), j = $(this).parents(e);
                i.eq(k + 2).removeClass("card3").removeClass("cardn");
                i.eq(k + 1).removeClass("card2").removeClass("cardn").addClass("card3");
                i.eq(k).removeClass("card1").removeClass("cardn").addClass("card2");
                setTimeout(function () {
                    i.eq(k - 1).addClass("card1").removeClass("cardn")
                }, 200)
            })
        }
    })
};