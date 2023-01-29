package com.zz.live.bean;

import java.io.Serializable;
import java.util.List;

public class LotteryEntitiy {


    /**
     * officialGameClassList : [{"game":4,"code":"shifenliuhe","currentLotteryTime":1592469000000,"openjgtime":600,"iscustom":1,"remark":"10分钟1期","currentLotteryQiShu":"2006180099","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":40,"createdUser":"sys","image":"upload/images/20200302/1583126595473.png","waitLotteryQiShu":"2006200108","lastLotteryNo":"28,23,49,20,26,12,17","lastModifiedDate":1592647203000,"isDelete":0,"type_id":2,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647200000,"tqlasttime":0,"version":1503,"lastLotteryQiShu":"2006200108","createdDate":1534931922000,"size":0,"gameTouZhuRadio":0,"isHot":1,"typename":"10分六合彩","listsort":0,"status":1},{"game":3,"code":"YFsaiche","currentLotteryTime":1592469000000,"openjgtime":60,"iscustom":1,"remark":"一分钟一期","currentLotteryQiShu":"2006180990","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":127,"createdUser":"kugua1","image":"upload/images/20200603/1591153038590.png","waitLotteryQiShu":"2006201085","lastLotteryNo":"1,9,8,10,5,2,7,3,4,6","lastModifiedDate":1592647502000,"isDelete":0,"type_id":78,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647500000,"tqlasttime":0,"version":26,"lastLotteryQiShu":"2006201085","createdDate":1591153007000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"1分赛车","listsort":2,"status":1},{"game":9,"code":"xuanwu","currentLotteryTime":1592470140000,"openjgtime":1200,"iscustom":0,"remark":"全天42期","currentLotteryQiShu":"200618023","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":22,"createdUser":"sys","image":"upload/images/20200302/1583127732256.png","waitLotteryQiShu":"200620026","lastLotteryNo":"6,3,7,9,1","lastModifiedDate":1592646694000,"isDelete":0,"type_id":1,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592646671000,"tqlasttime":0,"version":1303,"lastLotteryQiShu":"200620026","createdDate":1514451867000,"size":0,"gameTouZhuRadio":0,"isHot":1,"typename":"广东11选5","listsort":3,"status":1},{"game":8,"code":"xingyu","currentLotteryTime":1592469510000,"openjgtime":1200,"iscustom":0,"remark":"全天42期","currentLotteryQiShu":"20061823","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":20,"createdUser":"sys","image":"upload/images/20200302/1583127838917.png","waitLotteryQiShu":"20062027","lastLotteryNo":"20,3,9,19,5,14,4,18","lastModifiedDate":1592647321000,"isDelete":0,"type_id":1,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647303000,"tqlasttime":0,"version":1196,"lastLotteryQiShu":"20062027","createdDate":1514362584000,"size":0,"gameTouZhuRadio":0,"isHot":1,"typename":"广东快乐10分","listsort":4,"status":1},{"game":7,"code":"farm","currentLotteryTime":1592469480000,"openjgtime":1200,"iscustom":0,"remark":"全天59期","currentLotteryQiShu":"200618038","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":19,"createdUser":"sys","image":"upload/images/20200302/1583127899007.png","waitLotteryQiShu":"200620042","lastLotteryNo":"13,6,1,16,10,15,19,12","lastModifiedDate":1592647321000,"isDelete":0,"type_id":1,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647291000,"tqlasttime":0,"version":1212,"lastLotteryQiShu":"200620042","createdDate":1514362584000,"size":0,"gameTouZhuRadio":0,"isHot":1,"typename":"重庆幸运农场","listsort":5,"status":1},{"game":1,"code":"FHK3","currentLotteryTime":1592469000000,"openjgtime":60,"iscustom":1,"remark":"1分钟1期","currentLotteryQiShu":"2006180990","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":32,"createdUser":"sys","image":"upload/images/20200302/1583126811420.png","waitLotteryQiShu":"2006201085","lastLotteryNo":"4,3,6","lastModifiedDate":1592647503000,"isDelete":0,"type_id":2,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647500000,"tqlasttime":0,"version":1513,"lastLotteryQiShu":"2006201085","createdDate":1527860066000,"size":0,"gameTouZhuRadio":0,"isHot":1,"typename":"1分快3","listsort":6,"status":1},{"game":3,"code":"xyft168","currentLotteryTime":1592370510000,"openjgtime":300,"iscustom":1,"remark":"5分钟一期","currentLotteryQiShu":"200617001","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":121,"createdUser":"xbgoogle","image":"upload/images/20200523/1590220601212.png","waitLotteryQiShu":"200620060","lastLotteryNo":"2,1,10,8,6,3,5,4,7,9","lastModifiedDate":1592647475000,"isDelete":0,"type_id":77,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647444000,"tqlasttime":0,"version":116,"lastLotteryQiShu":"200620060","createdDate":1590220342000,"size":0,"gameTouZhuRadio":0,"isHot":1,"typename":"新幸运飞艇","listsort":8,"status":1},{"game":3,"code":"azxy10","currentLotteryTime":1592469120000,"openjgtime":300,"iscustom":0,"remark":"5分钟1期","currentLotteryQiShu":"20703124","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":63,"createdUser":"sys","image":"upload/images/20200226/1582713359593.png","waitLotteryQiShu":"20703718","lastLotteryNo":"8,7,2,6,3,1,9,4,5,10","lastModifiedDate":1592647475000,"isDelete":0,"type_id":10,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647442000,"tqlasttime":0,"version":1223,"lastLotteryQiShu":"20703718","createdDate":1560504416000,"size":0,"gameTouZhuRadio":0,"isHot":1,"typename":"澳洲幸运10","listsort":10,"status":1},{"game":2,"code":"azxy5","currentLotteryTime":1592469180000,"openjgtime":300,"iscustom":0,"remark":"5分钟1期","currentLotteryQiShu":"50694224","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":122,"createdUser":"xbgoogle","image":"upload/images/20200523/1590224617655.png","waitLotteryQiShu":"50694818","lastLotteryNo":"0,5,3,0,2","lastModifiedDate":1592647458000,"isDelete":0,"type_id":51,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647442000,"tqlasttime":0,"version":75,"lastLotteryQiShu":"50694818","createdDate":1590224514000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"澳洲幸运5","listsort":11,"status":1},{"game":3,"code":"BJrace","currentLotteryTime":1592470120000,"openjgtime":1200,"iscustom":0,"remark":"全天44期","currentLotteryQiShu":"746178","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":124,"createdUser":"xbgoogle","image":"upload/images/20200524/1590295075319.png","waitLotteryQiShu":"746269","lastLotteryNo":"7,2,1,3,9,10,8,6,5,4","lastModifiedDate":1592646754000,"isDelete":0,"type_id":1,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592646737000,"tqlasttime":0,"version":94,"lastLotteryQiShu":"746269","createdDate":1590294936000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"北京赛车","listsort":12,"status":1},{"game":2,"code":"jisushishicai","currentLotteryTime":1592469000000,"openjgtime":90,"iscustom":1,"remark":"全天957期","currentLotteryQiShu":"2006180660","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":42,"createdUser":"sys","image":"upload/images/20200226/1582711101210.png","waitLotteryQiShu":"2006200723","lastLotteryNo":"6,8,9,8,7","lastModifiedDate":1592647472000,"isDelete":0,"type_id":8,"index":0,"isLottery":1,"isStart":1,"lastLotteryTime":1592647470000,"tqlasttime":0,"version":1407,"lastLotteryQiShu":"2006200723","createdDate":1551110003000,"size":0,"gameTouZhuRadio":0,"isHot":1,"typename":"极速时时彩","listsort":13,"status":1},{"game":3,"code":"feiting","currentLotteryTime":1592469210000,"openjgtime":300,"iscustom":1,"remark":"全天180期","currentLotteryQiShu":"200618042","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"kugua","id":14,"createdUser":"sys","image":"upload/images/20200226/1582713271037.png","waitLotteryQiShu":"200617001","lastLotteryNo":"4,6,8,9,2,7,3,5,10,1","lastModifiedDate":1592468970000,"isDelete":0,"type_id":7,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1589054689000,"tqlasttime":0,"version":1271,"lastLotteryQiShu":"200509180","createdDate":1513063958000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"幸运飞艇","listsort":14,"status":1},{"game":2,"code":"CQsscai","currentLotteryTime":1592470080000,"openjgtime":1200,"iscustom":0,"remark":"全天59期","currentLotteryQiShu":"200618038","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":8,"createdUser":"sys","image":"upload/images/20200226/1582709814637.png","waitLotteryQiShu":"200620041","lastLotteryNo":"1,9,9,9,6","lastModifiedDate":1592646707000,"isDelete":0,"type_id":1,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592646696000,"tqlasttime":40,"version":1418,"lastLotteryQiShu":"200620041","createdDate":1512700661000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"欢乐生肖","listsort":16,"status":1},{"game":2,"code":"tjssc","currentLotteryTime":1592469480000,"openjgtime":1200,"iscustom":0,"remark":"全天42期","currentLotteryQiShu":"200618023","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":54,"createdUser":"sys","image":"upload/images/20200226/1582712427587.png","waitLotteryQiShu":"200620027","lastLotteryNo":"1,6,1,4,8","lastModifiedDate":1592647278000,"isDelete":0,"type_id":10,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647249000,"tqlasttime":0,"version":1434,"lastLotteryQiShu":"200620027","createdDate":1552662316000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"天津时时彩","listsort":17,"status":1},{"game":2,"code":"xjssc","currentLotteryTime":1592469480000,"openjgtime":1200,"iscustom":0,"remark":"全天48期","currentLotteryQiShu":"200618020","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":55,"createdUser":"sys","image":"upload/images/20200226/1582713077809.png","waitLotteryQiShu":"200620024","lastLotteryNo":"6,5,9,9,2","lastModifiedDate":1592647278000,"isDelete":0,"type_id":11,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647253000,"tqlasttime":0,"version":1420,"lastLotteryQiShu":"200620024","createdDate":1552662316000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"新疆时时彩","listsort":18,"status":1},{"game":2,"code":"tencentffc","currentLotteryTime":1592469000000,"openjgtime":60,"iscustom":1,"remark":"1分钟1期","currentLotteryQiShu":"2006180990","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":59,"createdUser":"sys","image":"upload/images/20200302/1583125587006.png","waitLotteryQiShu":"2006201085","lastLotteryNo":"2,2,1,9,4","lastModifiedDate":1592647502000,"isDelete":0,"type_id":12,"index":0,"isLottery":1,"isStart":1,"lastLotteryTime":1592647500000,"tqlasttime":0,"version":1379,"lastLotteryQiShu":"2006201085","createdDate":1559980198000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"腾讯分分彩","listsort":19,"status":1},{"game":3,"code":"wufen","currentLotteryTime":1592468990000,"openjgtime":300,"iscustom":1,"remark":"5分钟1期","currentLotteryQiShu":"200618198","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":53,"createdUser":"sys","image":"upload/images/20200226/1582713576280.png","waitLotteryQiShu":"200620217","lastLotteryNo":"10,8,5,1,6,7,9,4,3,2","lastModifiedDate":1592647511000,"isDelete":0,"type_id":8,"index":0,"isLottery":1,"isStart":1,"lastLotteryTime":1592647500000,"tqlasttime":10,"version":1287,"lastLotteryQiShu":"200620217","createdDate":1552494782000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"5分赛车","listsort":20,"status":1},{"game":3,"code":"xn","currentLotteryTime":1592469000000,"openjgtime":90,"iscustom":1,"remark":"全天956期","currentLotteryQiShu":"200618660","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":43,"createdUser":"sys","image":"upload/images/20200302/1583125660500.png","waitLotteryQiShu":"200620723","lastLotteryNo":"10,9,1,5,2,3,7,6,4,8","lastModifiedDate":1592647472000,"isDelete":0,"type_id":6,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647470000,"tqlasttime":0,"version":1273,"lastLotteryQiShu":"200620723","createdDate":1551110003000,"size":0,"gameTouZhuRadio":0,"isHot":1,"typename":"幸运赛车","listsort":21,"status":1},{"game":4,"code":"liuhe","currentLotteryTime":1592659790000,"openjgtime":2,"iscustom":0,"remark":"每周3期","currentLotteryQiShu":"2020068","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":15,"createdUser":"sys","image":"upload/images/20200302/1583126565719.png","waitLotteryQiShu":"2020068","lastLotteryNo":"31,43,05,30,03,48,13","lastModifiedDate":1592564472000,"isDelete":0,"type_id":1,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1580564081000,"tqlasttime":10,"version":1522,"lastLotteryQiShu":"2020008","createdDate":1513330453000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"香港六合彩","listsort":22,"status":1},{"game":1,"code":"HXK3","currentLotteryTime":1592470080000,"openjgtime":1200,"iscustom":0,"remark":"全天40期","currentLotteryQiShu":"200618023","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":46,"createdUser":"sys","image":"upload/images/20200302/1583126647253.png","waitLotteryQiShu":"200620026","lastLotteryNo":"4,5,6","lastModifiedDate":1592646596000,"isDelete":0,"type_id":8,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592646577000,"tqlasttime":0,"version":1534,"lastLotteryQiShu":"200620026","createdDate":1552110671000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"广西快3","listsort":23,"status":1},{"game":1,"code":"BJK3","currentLotteryTime":1592469600000,"openjgtime":1200,"iscustom":0,"remark":"全天44期","currentLotteryQiShu":"149739","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":45,"createdUser":"sys","image":"upload/images/20200302/1583126681570.png","waitLotteryQiShu":"149831","lastLotteryNo":"2,2,6","lastModifiedDate":1592647258000,"isDelete":0,"type_id":7,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647240000,"tqlasttime":0,"version":1534,"lastLotteryQiShu":"149831","createdDate":1552110671000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"北京快3","listsort":24,"status":1},{"game":1,"code":"JLK3","currentLotteryTime":1592469480000,"openjgtime":1200,"iscustom":0,"remark":"全天40期","currentLotteryQiShu":"200618025","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":48,"createdUser":"sys","image":"upload/images/20200302/1583126695655.png","waitLotteryQiShu":"200620029","lastLotteryNo":"6,6,6","lastModifiedDate":1592647228000,"isDelete":0,"type_id":11,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647185000,"tqlasttime":0,"version":1535,"lastLotteryQiShu":"200620029","createdDate":1552110671000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"吉林快3","listsort":25,"status":1},{"game":1,"code":"NHK3","currentLotteryTime":1592469480000,"openjgtime":1200,"iscustom":0,"remark":"全天40期","currentLotteryQiShu":"200618024","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":47,"createdUser":"sys","image":"upload/images/20200302/1583126710575.png","waitLotteryQiShu":"200620028","lastLotteryNo":"2,2,4","lastModifiedDate":1592647228000,"isDelete":0,"type_id":9,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647205000,"tqlasttime":0,"version":1556,"lastLotteryQiShu":"200620028","createdDate":1552110671000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"安徽快3","listsort":26,"status":1},{"game":1,"code":"NMGK3","currentLotteryTime":1592469480000,"openjgtime":1200,"iscustom":0,"remark":"全天36期","currentLotteryQiShu":"200618021","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":50,"createdUser":"sys","image":"upload/images/20200302/1583126750189.png","waitLotteryQiShu":"200620024","lastLotteryNo":"2,4,5","lastModifiedDate":1592646386000,"isDelete":0,"type_id":13,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592646365000,"tqlasttime":0,"version":1561,"lastLotteryQiShu":"200620024","createdDate":1552110671000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"内蒙古快3","listsort":27,"status":1},{"game":1,"code":"FJK3","currentLotteryTime":1592470080000,"openjgtime":1200,"iscustom":0,"remark":"全天42期","currentLotteryQiShu":"200618025","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":51,"createdUser":"sys","image":"upload/images/20200302/1583126763699.png","waitLotteryQiShu":"200620028","lastLotteryNo":"1,1,1","lastModifiedDate":1592646867000,"isDelete":0,"type_id":14,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592646800000,"tqlasttime":0,"version":1547,"lastLotteryQiShu":"200620028","createdDate":1552110671000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"福建快3","listsort":28,"status":1},{"game":1,"code":"GSK3","currentLotteryTime":1592469480000,"openjgtime":1200,"iscustom":0,"remark":"全天36期","currentLotteryQiShu":"200618020","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":52,"createdUser":"sys","image":"upload/images/20200302/1583126779935.png","waitLotteryQiShu":"200620024","lastLotteryNo":"1,4,6","lastModifiedDate":1592647228000,"isDelete":0,"type_id":15,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647178000,"tqlasttime":0,"version":1551,"lastLotteryQiShu":"200620024","createdDate":1552110671000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"甘肃快3","listsort":29,"status":1},{"game":1,"code":"SHK3","currentLotteryTime":1592469480000,"openjgtime":1200,"iscustom":0,"remark":"全天41期","currentLotteryQiShu":"200618024","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":33,"createdUser":"sys","image":"upload/images/20200302/1583126794975.png","waitLotteryQiShu":"200620028","lastLotteryNo":"4,4,6","lastModifiedDate":1592647137000,"isDelete":0,"type_id":4,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647132000,"tqlasttime":0,"version":1549,"lastLotteryQiShu":"200620028","createdDate":1528276455000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"上海快3","listsort":30,"status":1},{"game":1,"code":"sfks","currentLotteryTime":1592468990000,"openjgtime":180,"iscustom":1,"remark":"3分钟1期","currentLotteryQiShu":"2006180330","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":88,"createdUser":"xbgoogle","image":"upload/images/20200302/1583126867266.png","waitLotteryQiShu":"2006200361","lastLotteryNo":"5,5,3","lastModifiedDate":1592647383000,"isDelete":0,"type_id":25,"index":0,"isLottery":1,"isStart":1,"lastLotteryTime":1592647380000,"tqlasttime":10,"version":624,"lastLotteryQiShu":"2006200361","createdDate":1578049109000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"3分快3","listsort":31,"status":1},{"game":1,"code":"5fk3","currentLotteryTime":1592468990000,"openjgtime":300,"iscustom":1,"remark":"5分钟1期","currentLotteryQiShu":"2006180198","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":78,"createdUser":"xbgoogle","image":"upload/images/20200302/1583126881556.png","waitLotteryQiShu":"2006200217","lastLotteryNo":"2,1,3","lastModifiedDate":1592647503000,"isDelete":0,"type_id":24,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647500000,"tqlasttime":10,"version":1086,"lastLotteryQiShu":"2006200217","createdDate":1568258147000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"5分快3","listsort":32,"status":1},{"game":1,"code":"sfk3","currentLotteryTime":1592469480000,"openjgtime":600,"iscustom":1,"remark":"10分钟1期","currentLotteryQiShu":"2006180100","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":89,"createdUser":"xbgoogle","image":"upload/images/20200302/1583126898086.png","waitLotteryQiShu":"2006200108","lastLotteryNo":"3,6,2","lastModifiedDate":1592647202000,"isDelete":0,"type_id":26,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647200000,"tqlasttime":120,"version":618,"lastLotteryQiShu":"2006200108","createdDate":1578055989000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"10分快3","listsort":33,"status":1},{"game":1,"code":"JXK3","currentLotteryTime":1592469180000,"openjgtime":1200,"iscustom":0,"remark":"全天42期","currentLotteryQiShu":"200618025","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":110,"createdUser":"admin","image":"upload/images/20200302/1583127570284.png","waitLotteryQiShu":"200620029","lastLotteryNo":"1,4,4","lastModifiedDate":1592646807000,"isDelete":0,"type_id":12,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592646782000,"tqlasttime":0,"version":475,"lastLotteryQiShu":"200620029","createdDate":1580209907000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"江西快3","listsort":34,"status":1},{"game":9,"code":"SDxuanwu","currentLotteryTime":1592469630000,"openjgtime":1200,"iscustom":0,"remark":"全天43期","currentLotteryQiShu":"200618024","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":38,"createdUser":"sys","image":"upload/images/20200302/1583127625555.png","waitLotteryQiShu":"200620028","lastLotteryNo":"3,4,6,10,1","lastModifiedDate":1592647325000,"isDelete":0,"type_id":3,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647320000,"tqlasttime":0,"version":1304,"lastLotteryQiShu":"200620028","createdDate":1528276455000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"山东11选5","listsort":35,"status":1},{"game":9,"code":"SHxuanwu","currentLotteryTime":1592469480000,"openjgtime":1200,"iscustom":0,"remark":"全天45期","currentLotteryQiShu":"200618023","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":37,"createdUser":"sys","image":"upload/images/20200302/1583127644759.png","waitLotteryQiShu":"200620027","lastLotteryNo":"5,1,9,3,4","lastModifiedDate":1592647355000,"isDelete":0,"type_id":2,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647305000,"tqlasttime":0,"version":1298,"lastLotteryQiShu":"200620027","createdDate":1528276455000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"上海11选5","listsort":36,"status":1},{"game":9,"code":"5fen","currentLotteryTime":1592468990000,"openjgtime":300,"iscustom":1,"remark":"5分钟1期","currentLotteryQiShu":"200618198","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":65,"createdUser":"sys","image":"upload/images/20200302/1583127658650.png","waitLotteryQiShu":"200620217","lastLotteryNo":"4,3,5,9,8","lastModifiedDate":1592647502000,"isDelete":0,"type_id":6,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647500000,"tqlasttime":10,"version":1186,"lastLotteryQiShu":"200620217","createdDate":1564199687000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"5分11选5","listsort":37,"status":1},{"game":9,"code":"JXxuanwu","currentLotteryTime":1592470020000,"openjgtime":1200,"iscustom":0,"remark":"全天42期","currentLotteryQiShu":"200618023","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":39,"createdUser":"sys","image":"upload/images/20200302/1583127717819.png","waitLotteryQiShu":"200620026","lastLotteryNo":"9,6,7,3,5","lastModifiedDate":1592646694000,"isDelete":0,"type_id":4,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592646677000,"tqlasttime":0,"version":1302,"lastLotteryQiShu":"200620026","createdDate":1528276455000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"江西11选5","listsort":38,"status":1},{"game":5,"code":"rddd","currentLotteryTime":1592468990000,"openjgtime":180,"iscustom":1,"remark":"3分钟1期","currentLotteryQiShu":"2006180330","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":44,"createdUser":"sys","image":"upload/images/20200302/1583127764232.png","waitLotteryQiShu":"2006200361","lastLotteryNo":"8,1,2","lastModifiedDate":1592647382000,"isDelete":0,"type_id":2,"index":0,"isLottery":1,"isStart":1,"lastLotteryTime":1592647380000,"tqlasttime":10,"version":1289,"lastLotteryQiShu":"2006200361","createdDate":1551236135000,"size":0,"gameTouZhuRadio":0,"isHot":1,"typename":"瑞典蛋蛋","listsort":39,"status":1},{"game":5,"code":"js28","currentLotteryTime":1592469000000,"openjgtime":60,"iscustom":1,"remark":"1分钟1期","currentLotteryQiShu":"2006180990","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":57,"createdUser":"sys","image":"upload/images/20200302/1583127785871.png","waitLotteryQiShu":"2006201085","lastLotteryNo":"3,0,2","lastModifiedDate":1592647502000,"isDelete":0,"type_id":6,"index":0,"isLottery":1,"isStart":1,"lastLotteryTime":1592647500000,"tqlasttime":0,"version":1256,"lastLotteryQiShu":"2006201085","createdDate":1554010252000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"极速28","listsort":40,"status":1},{"game":5,"code":"jnd28","currentLotteryTime":1592469178000,"openjgtime":200,"iscustom":0,"remark":"全天964期","currentLotteryQiShu":"2582921","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":56,"createdUser":"sys","image":"upload/images/20200302/1583127806472.png","waitLotteryQiShu":"2583721","lastLotteryNo":"9,9,0","lastModifiedDate":1592647478000,"isDelete":0,"type_id":5,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592647462000,"tqlasttime":0,"version":1246,"lastLotteryQiShu":"2583721","createdDate":1552662316000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"加拿大28","listsort":41,"status":1},{"game":1,"code":"HEBK3","currentLotteryTime":1592470079000,"openjgtime":1200,"iscustom":0,"remark":"全天41期","currentLotteryQiShu":"200618025","gameTouZhuAmount":0,"changeVersion":false,"isopenOffice":1,"lastModifiedUser":"sys","id":35,"createdUser":"sys","image":"upload/images/20200302/1583127914345.png","waitLotteryQiShu":"200620028","lastLotteryNo":"1,5,5","lastModifiedDate":1592646626000,"isDelete":0,"type_id":6,"index":0,"isLottery":0,"isStart":1,"lastLotteryTime":1592646578000,"tqlasttime":1,"version":1533,"lastLotteryQiShu":"200620028","createdDate":1528276455000,"size":0,"gameTouZhuRadio":0,"isHot":0,"typename":"河北快3","listsort":42,"status":1}]
     * zhaiyaoList : [{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":0,"typeName":"撤单返还","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":1,"typeName":"中奖获得","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":2,"typeName":"后台入款","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":6,"typeName":"活动红包","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":7,"typeName":"生日红包","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":8,"typeName":"晋级奖励","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":9,"typeName":"每日加奖","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":11,"typeName":"前台入款","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":12,"typeName":"充值返点","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":13,"typeName":"上级充值","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":15,"typeName":"抢普通红包","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":16,"typeName":"红包退回","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":22,"typeName":"其他优惠","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":23,"typeName":"转入余额宝","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":24,"typeName":"邀请红包","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":25,"typeName":"天降红包","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":26,"typeName":"专享红包","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":50,"typeName":"三方转到余额","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":101,"typeName":"投注扣款","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":104,"typeName":"代理佣金给充值","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":107,"typeName":"提现扣款","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":109,"typeName":"发普通红包","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":110,"typeName":"余额宝转出","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":111,"typeName":"打赏主播礼物","type_id":0},{"amount":0,"game":0,"isDelete":0,"isTest":0,"orderType":0,"play_type":0,"price":0,"price2":0,"source":0,"type":150,"typeName":"余额转到三方","type_id":0}]
     * classtypelist : [{"gamename":"快3","game":1,"id":2},{"gamename":"时时彩","game":2,"id":1},{"gamename":"赛车","game":3,"id":7},{"gamename":"六合彩","game":4,"id":1},{"gamename":"pc蛋蛋","game":5,"id":2},{"gamename":"快乐8","game":6,"id":1},{"gamename":"幸运农场","game":7,"id":1},{"gamename":"快乐十分","game":8,"id":1},{"gamename":"11选5","game":9,"id":1}]
     * message : success
     * gameClasslist : [{"changeVersion":false,"code":"shifenliuhe","createdDate":1534931922000,"createdUser":"sys","currentLotteryQiShu":"2006180099","currentLotteryTime":1592469000000,"game":4,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":40,"image":"upload/images/20200302/1583126595473.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"28,23,49,20,26,12,17","lastLotteryQiShu":"2006200108","lastLotteryTime":1592647200000,"lastModifiedDate":1592647203000,"lastModifiedUser":"sys","listsort":0,"openjgtime":600,"remark":"10分钟1期","size":0,"status":1,"tqlasttime":0,"type_id":2,"typename":"10分六合彩","version":1503,"waitLotteryQiShu":"2006200108"},{"changeVersion":false,"code":"yifenliuhe","createdDate":1581074897000,"createdUser":"xbgoogle","currentLotteryQiShu":"2006180990","currentLotteryTime":1592469000000,"game":4,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":111,"image":"upload/images/20200302/1583129853904.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":0,"lastLotteryNo":"35,18,31,06,28,39,13","lastLotteryQiShu":"2006201085","lastLotteryTime":1592647500000,"lastModifiedDate":1592647501000,"lastModifiedUser":"sys","listsort":1,"openjgtime":60,"remark":"1分钟1期","size":0,"status":1,"tqlasttime":0,"type_id":3,"typename":"1分六合彩","version":439,"waitLotteryQiShu":"2006201085"},{"changeVersion":false,"code":"YFsaiche","createdDate":1591153007000,"createdUser":"kugua1","currentLotteryQiShu":"2006180990","currentLotteryTime":1592469000000,"game":3,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":127,"image":"upload/images/20200603/1591153038590.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"1,9,8,10,5,2,7,3,4,6","lastLotteryQiShu":"2006201085","lastLotteryTime":1592647500000,"lastModifiedDate":1592647502000,"lastModifiedUser":"sys","listsort":2,"openjgtime":60,"remark":"一分钟一期","size":0,"status":1,"tqlasttime":0,"type_id":78,"typename":"1分赛车","version":26,"waitLotteryQiShu":"2006201085"},{"changeVersion":false,"code":"xuanwu","createdDate":1514451867000,"createdUser":"sys","currentLotteryQiShu":"200618023","currentLotteryTime":1592470140000,"game":9,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":22,"image":"upload/images/20200302/1583127732256.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"6,3,7,9,1","lastLotteryQiShu":"200620026","lastLotteryTime":1592646671000,"lastModifiedDate":1592646694000,"lastModifiedUser":"sys","listsort":3,"openjgtime":1200,"remark":"全天42期","size":0,"status":1,"tqlasttime":0,"type_id":1,"typename":"广东11选5","version":1303,"waitLotteryQiShu":"200620026"},{"changeVersion":false,"code":"xingyu","createdDate":1514362584000,"createdUser":"sys","currentLotteryQiShu":"20061823","currentLotteryTime":1592469510000,"game":8,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":20,"image":"upload/images/20200302/1583127838917.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"20,3,9,19,5,14,4,18","lastLotteryQiShu":"20062027","lastLotteryTime":1592647303000,"lastModifiedDate":1592647321000,"lastModifiedUser":"sys","listsort":4,"openjgtime":1200,"remark":"全天42期","size":0,"status":1,"tqlasttime":0,"type_id":1,"typename":"广东快乐10分","version":1196,"waitLotteryQiShu":"20062027"},{"changeVersion":false,"code":"farm","createdDate":1514362584000,"createdUser":"sys","currentLotteryQiShu":"200618038","currentLotteryTime":1592469480000,"game":7,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":19,"image":"upload/images/20200302/1583127899007.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"13,6,1,16,10,15,19,12","lastLotteryQiShu":"200620042","lastLotteryTime":1592647291000,"lastModifiedDate":1592647321000,"lastModifiedUser":"sys","listsort":5,"openjgtime":1200,"remark":"全天59期","size":0,"status":1,"tqlasttime":0,"type_id":1,"typename":"重庆幸运农场","version":1212,"waitLotteryQiShu":"200620042"},{"changeVersion":false,"code":"FHK3","createdDate":1527860066000,"createdUser":"sys","currentLotteryQiShu":"2006180990","currentLotteryTime":1592469000000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":32,"image":"upload/images/20200302/1583126811420.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"4,3,6","lastLotteryQiShu":"2006201085","lastLotteryTime":1592647500000,"lastModifiedDate":1592647503000,"lastModifiedUser":"sys","listsort":6,"openjgtime":60,"remark":"1分钟1期","size":0,"status":1,"tqlasttime":0,"type_id":2,"typename":"1分快3","version":1513,"waitLotteryQiShu":"2006201085"},{"changeVersion":false,"code":"happy8","createdDate":1590294936000,"createdUser":"xbgoogle","currentLotteryQiShu":"1003658","currentLotteryTime":1592469210000,"game":6,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":126,"image":"upload/images/20200531/1590904746878.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":0,"lastLotteryNo":"8,13,15,18,19,21,27,29,35,36,37,41,47,60,62,64,69,71,73,77","lastLotteryQiShu":"1004033","lastLotteryTime":1592647336000,"lastModifiedDate":1592647381000,"lastModifiedUser":"sys","listsort":7,"openjgtime":300,"remark":"全天179期","size":0,"status":1,"tqlasttime":0,"type_id":1,"typename":"北京快乐8","version":59,"waitLotteryQiShu":"1004033"},{"changeVersion":false,"code":"xyft168","createdDate":1590220342000,"createdUser":"xbgoogle","currentLotteryQiShu":"200617001","currentLotteryTime":1592370510000,"game":3,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":121,"image":"upload/images/20200523/1590220601212.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"2,1,10,8,6,3,5,4,7,9","lastLotteryQiShu":"200620060","lastLotteryTime":1592647444000,"lastModifiedDate":1592647475000,"lastModifiedUser":"sys","listsort":8,"openjgtime":300,"remark":"5分钟一期","size":0,"status":1,"tqlasttime":0,"type_id":77,"typename":"新幸运飞艇","version":116,"waitLotteryQiShu":"200620060"},{"changeVersion":false,"code":"dan","createdDate":1590294936000,"createdUser":"xbgoogle","currentLotteryQiShu":"1003658","currentLotteryTime":1592469260000,"game":5,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":125,"image":"upload/images/20200524/1590295008839.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":0,"lastLotteryNo":"4,5,3","lastLotteryQiShu":"1004033","lastLotteryTime":1592647336000,"lastModifiedDate":1592647388000,"lastModifiedUser":"sys","listsort":9,"openjgtime":300,"remark":"全天179期","size":0,"status":1,"tqlasttime":10,"type_id":1,"typename":"北京28","version":60,"waitLotteryQiShu":"1004033"},{"changeVersion":false,"code":"azxy10","createdDate":1560504416000,"createdUser":"sys","currentLotteryQiShu":"20703124","currentLotteryTime":1592469120000,"game":3,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":63,"image":"upload/images/20200226/1582713359593.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"8,7,2,6,3,1,9,4,5,10","lastLotteryQiShu":"20703718","lastLotteryTime":1592647442000,"lastModifiedDate":1592647475000,"lastModifiedUser":"sys","listsort":10,"openjgtime":300,"remark":"5分钟1期","size":0,"status":1,"tqlasttime":0,"type_id":10,"typename":"澳洲幸运10","version":1223,"waitLotteryQiShu":"20703718"},{"changeVersion":false,"code":"azxy5","createdDate":1590224514000,"createdUser":"xbgoogle","currentLotteryQiShu":"50694224","currentLotteryTime":1592469180000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":122,"image":"upload/images/20200523/1590224617655.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"0,5,3,0,2","lastLotteryQiShu":"50694818","lastLotteryTime":1592647442000,"lastModifiedDate":1592647458000,"lastModifiedUser":"sys","listsort":11,"openjgtime":300,"remark":"5分钟1期","size":0,"status":1,"tqlasttime":0,"type_id":51,"typename":"澳洲幸运5","version":75,"waitLotteryQiShu":"50694818"},{"changeVersion":false,"code":"BJrace","createdDate":1590294936000,"createdUser":"xbgoogle","currentLotteryQiShu":"746178","currentLotteryTime":1592470120000,"game":3,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":124,"image":"upload/images/20200524/1590295075319.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"7,2,1,3,9,10,8,6,5,4","lastLotteryQiShu":"746269","lastLotteryTime":1592646737000,"lastModifiedDate":1592646754000,"lastModifiedUser":"sys","listsort":12,"openjgtime":1200,"remark":"全天44期","size":0,"status":1,"tqlasttime":0,"type_id":1,"typename":"北京赛车","version":94,"waitLotteryQiShu":"746269"},{"changeVersion":false,"code":"jisushishicai","createdDate":1551110003000,"createdUser":"sys","currentLotteryQiShu":"2006180660","currentLotteryTime":1592469000000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":42,"image":"upload/images/20200226/1582711101210.png","index":0,"isDelete":0,"isHot":1,"isLottery":1,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"6,8,9,8,7","lastLotteryQiShu":"2006200723","lastLotteryTime":1592647470000,"lastModifiedDate":1592647472000,"lastModifiedUser":"sys","listsort":13,"openjgtime":90,"remark":"全天957期","size":0,"status":1,"tqlasttime":0,"type_id":8,"typename":"极速时时彩","version":1407,"waitLotteryQiShu":"2006200723"},{"changeVersion":false,"code":"feiting","createdDate":1513063958000,"createdUser":"sys","currentLotteryQiShu":"200618042","currentLotteryTime":1592469210000,"game":3,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":14,"image":"upload/images/20200226/1582713271037.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"4,6,8,9,2,7,3,5,10,1","lastLotteryQiShu":"200509180","lastLotteryTime":1589054689000,"lastModifiedDate":1592468970000,"lastModifiedUser":"kugua","listsort":14,"openjgtime":300,"remark":"全天180期","size":0,"status":1,"tqlasttime":0,"type_id":7,"typename":"幸运飞艇","version":1271,"waitLotteryQiShu":"200617001"},{"changeVersion":false,"code":"CQsscai","createdDate":1512700661000,"createdUser":"sys","currentLotteryQiShu":"200618038","currentLotteryTime":1592470080000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":8,"image":"upload/images/20200226/1582709814637.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"1,9,9,9,6","lastLotteryQiShu":"200620041","lastLotteryTime":1592646696000,"lastModifiedDate":1592646707000,"lastModifiedUser":"sys","listsort":16,"openjgtime":1200,"remark":"全天59期","size":0,"status":1,"tqlasttime":40,"type_id":1,"typename":"欢乐生肖","version":1418,"waitLotteryQiShu":"200620041"},{"changeVersion":false,"code":"tjssc","createdDate":1552662316000,"createdUser":"sys","currentLotteryQiShu":"200618023","currentLotteryTime":1592469480000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":54,"image":"upload/images/20200226/1582712427587.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"1,6,1,4,8","lastLotteryQiShu":"200620027","lastLotteryTime":1592647249000,"lastModifiedDate":1592647278000,"lastModifiedUser":"sys","listsort":17,"openjgtime":1200,"remark":"全天42期","size":0,"status":1,"tqlasttime":0,"type_id":10,"typename":"天津时时彩","version":1434,"waitLotteryQiShu":"200620027"},{"changeVersion":false,"code":"xjssc","createdDate":1552662316000,"createdUser":"sys","currentLotteryQiShu":"200618020","currentLotteryTime":1592469480000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":55,"image":"upload/images/20200226/1582713077809.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"6,5,9,9,2","lastLotteryQiShu":"200620024","lastLotteryTime":1592647253000,"lastModifiedDate":1592647278000,"lastModifiedUser":"sys","listsort":18,"openjgtime":1200,"remark":"全天48期","size":0,"status":1,"tqlasttime":0,"type_id":11,"typename":"新疆时时彩","version":1420,"waitLotteryQiShu":"200620024"},{"changeVersion":false,"code":"tencentffc","createdDate":1559980198000,"createdUser":"sys","currentLotteryQiShu":"2006180990","currentLotteryTime":1592469000000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":59,"image":"upload/images/20200302/1583125587006.png","index":0,"isDelete":0,"isHot":0,"isLottery":1,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"2,2,1,9,4","lastLotteryQiShu":"2006201085","lastLotteryTime":1592647500000,"lastModifiedDate":1592647502000,"lastModifiedUser":"sys","listsort":19,"openjgtime":60,"remark":"1分钟1期","size":0,"status":1,"tqlasttime":0,"type_id":12,"typename":"腾讯分分彩","version":1379,"waitLotteryQiShu":"2006201085"},{"changeVersion":false,"code":"wufen","createdDate":1552494782000,"createdUser":"sys","currentLotteryQiShu":"200618198","currentLotteryTime":1592468990000,"game":3,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":53,"image":"upload/images/20200226/1582713576280.png","index":0,"isDelete":0,"isHot":0,"isLottery":1,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"10,8,5,1,6,7,9,4,3,2","lastLotteryQiShu":"200620217","lastLotteryTime":1592647500000,"lastModifiedDate":1592647511000,"lastModifiedUser":"sys","listsort":20,"openjgtime":300,"remark":"5分钟1期","size":0,"status":1,"tqlasttime":10,"type_id":8,"typename":"5分赛车","version":1287,"waitLotteryQiShu":"200620217"},{"changeVersion":false,"code":"xn","createdDate":1551110003000,"createdUser":"sys","currentLotteryQiShu":"200618660","currentLotteryTime":1592469000000,"game":3,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":43,"image":"upload/images/20200302/1583125660500.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"10,9,1,5,2,3,7,6,4,8","lastLotteryQiShu":"200620723","lastLotteryTime":1592647470000,"lastModifiedDate":1592647472000,"lastModifiedUser":"sys","listsort":21,"openjgtime":90,"remark":"全天956期","size":0,"status":1,"tqlasttime":0,"type_id":6,"typename":"幸运赛车","version":1273,"waitLotteryQiShu":"200620723"},{"changeVersion":false,"code":"liuhe","createdDate":1513330453000,"createdUser":"sys","currentLotteryQiShu":"2020068","currentLotteryTime":1592659790000,"game":4,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":15,"image":"upload/images/20200302/1583126565719.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"31,43,05,30,03,48,13","lastLotteryQiShu":"2020008","lastLotteryTime":1580564081000,"lastModifiedDate":1592564472000,"lastModifiedUser":"sys","listsort":22,"openjgtime":2,"remark":"每周3期","size":0,"status":1,"tqlasttime":10,"type_id":1,"typename":"香港六合彩","version":1522,"waitLotteryQiShu":"2020068"},{"changeVersion":false,"code":"HXK3","createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"200618023","currentLotteryTime":1592470080000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":46,"image":"upload/images/20200302/1583126647253.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"4,5,6","lastLotteryQiShu":"200620026","lastLotteryTime":1592646577000,"lastModifiedDate":1592646596000,"lastModifiedUser":"sys","listsort":23,"openjgtime":1200,"remark":"全天40期","size":0,"status":1,"tqlasttime":0,"type_id":8,"typename":"广西快3","version":1534,"waitLotteryQiShu":"200620026"},{"changeVersion":false,"code":"BJK3","createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"149739","currentLotteryTime":1592469600000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":45,"image":"upload/images/20200302/1583126681570.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"2,2,6","lastLotteryQiShu":"149831","lastLotteryTime":1592647240000,"lastModifiedDate":1592647258000,"lastModifiedUser":"sys","listsort":24,"openjgtime":1200,"remark":"全天44期","size":0,"status":1,"tqlasttime":0,"type_id":7,"typename":"北京快3","version":1534,"waitLotteryQiShu":"149831"},{"changeVersion":false,"code":"JLK3","createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"200618025","currentLotteryTime":1592469480000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":48,"image":"upload/images/20200302/1583126695655.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"6,6,6","lastLotteryQiShu":"200620029","lastLotteryTime":1592647185000,"lastModifiedDate":1592647228000,"lastModifiedUser":"sys","listsort":25,"openjgtime":1200,"remark":"全天40期","size":0,"status":1,"tqlasttime":0,"type_id":11,"typename":"吉林快3","version":1535,"waitLotteryQiShu":"200620029"},{"changeVersion":false,"code":"NHK3","createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"200618024","currentLotteryTime":1592469480000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":47,"image":"upload/images/20200302/1583126710575.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"2,2,4","lastLotteryQiShu":"200620028","lastLotteryTime":1592647205000,"lastModifiedDate":1592647228000,"lastModifiedUser":"sys","listsort":26,"openjgtime":1200,"remark":"全天40期","size":0,"status":1,"tqlasttime":0,"type_id":9,"typename":"安徽快3","version":1556,"waitLotteryQiShu":"200620028"},{"changeVersion":false,"code":"NMGK3","createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"200618021","currentLotteryTime":1592469480000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":50,"image":"upload/images/20200302/1583126750189.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"2,4,5","lastLotteryQiShu":"200620024","lastLotteryTime":1592646365000,"lastModifiedDate":1592646386000,"lastModifiedUser":"sys","listsort":27,"openjgtime":1200,"remark":"全天36期","size":0,"status":1,"tqlasttime":0,"type_id":13,"typename":"内蒙古快3","version":1561,"waitLotteryQiShu":"200620024"},{"changeVersion":false,"code":"FJK3","createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"200618025","currentLotteryTime":1592470080000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":51,"image":"upload/images/20200302/1583126763699.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"1,1,1","lastLotteryQiShu":"200620028","lastLotteryTime":1592646800000,"lastModifiedDate":1592646867000,"lastModifiedUser":"sys","listsort":28,"openjgtime":1200,"remark":"全天42期","size":0,"status":1,"tqlasttime":0,"type_id":14,"typename":"福建快3","version":1547,"waitLotteryQiShu":"200620028"},{"changeVersion":false,"code":"GSK3","createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"200618020","currentLotteryTime":1592469480000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":52,"image":"upload/images/20200302/1583126779935.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"1,4,6","lastLotteryQiShu":"200620024","lastLotteryTime":1592647178000,"lastModifiedDate":1592647228000,"lastModifiedUser":"sys","listsort":29,"openjgtime":1200,"remark":"全天36期","size":0,"status":1,"tqlasttime":0,"type_id":15,"typename":"甘肃快3","version":1551,"waitLotteryQiShu":"200620024"},{"changeVersion":false,"code":"SHK3","createdDate":1528276455000,"createdUser":"sys","currentLotteryQiShu":"200618024","currentLotteryTime":1592469480000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":33,"image":"upload/images/20200302/1583126794975.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"4,4,6","lastLotteryQiShu":"200620028","lastLotteryTime":1592647132000,"lastModifiedDate":1592647137000,"lastModifiedUser":"sys","listsort":30,"openjgtime":1200,"remark":"全天41期","size":0,"status":1,"tqlasttime":0,"type_id":4,"typename":"上海快3","version":1549,"waitLotteryQiShu":"200620028"},{"changeVersion":false,"code":"sfks","createdDate":1578049109000,"createdUser":"xbgoogle","currentLotteryQiShu":"2006180330","currentLotteryTime":1592468990000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":88,"image":"upload/images/20200302/1583126867266.png","index":0,"isDelete":0,"isHot":0,"isLottery":1,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"5,5,3","lastLotteryQiShu":"2006200361","lastLotteryTime":1592647380000,"lastModifiedDate":1592647383000,"lastModifiedUser":"sys","listsort":31,"openjgtime":180,"remark":"3分钟1期","size":0,"status":1,"tqlasttime":10,"type_id":25,"typename":"3分快3","version":624,"waitLotteryQiShu":"2006200361"},{"changeVersion":false,"code":"5fk3","createdDate":1568258147000,"createdUser":"xbgoogle","currentLotteryQiShu":"2006180198","currentLotteryTime":1592468990000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":78,"image":"upload/images/20200302/1583126881556.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"2,1,3","lastLotteryQiShu":"2006200217","lastLotteryTime":1592647500000,"lastModifiedDate":1592647503000,"lastModifiedUser":"sys","listsort":32,"openjgtime":300,"remark":"5分钟1期","size":0,"status":1,"tqlasttime":10,"type_id":24,"typename":"5分快3","version":1086,"waitLotteryQiShu":"2006200217"},{"changeVersion":false,"code":"sfk3","createdDate":1578055989000,"createdUser":"xbgoogle","currentLotteryQiShu":"2006180100","currentLotteryTime":1592469480000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":89,"image":"upload/images/20200302/1583126898086.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"3,6,2","lastLotteryQiShu":"2006200108","lastLotteryTime":1592647200000,"lastModifiedDate":1592647202000,"lastModifiedUser":"sys","listsort":33,"openjgtime":600,"remark":"10分钟1期","size":0,"status":1,"tqlasttime":120,"type_id":26,"typename":"10分快3","version":618,"waitLotteryQiShu":"2006200108"},{"changeVersion":false,"code":"JXK3","createdDate":1580209907000,"createdUser":"admin","currentLotteryQiShu":"200618025","currentLotteryTime":1592469180000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":110,"image":"upload/images/20200302/1583127570284.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"1,4,4","lastLotteryQiShu":"200620029","lastLotteryTime":1592646782000,"lastModifiedDate":1592646807000,"lastModifiedUser":"sys","listsort":34,"openjgtime":1200,"remark":"全天42期","size":0,"status":1,"tqlasttime":0,"type_id":12,"typename":"江西快3","version":475,"waitLotteryQiShu":"200620029"},{"changeVersion":false,"code":"SDxuanwu","createdDate":1528276455000,"createdUser":"sys","currentLotteryQiShu":"200618024","currentLotteryTime":1592469630000,"game":9,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":38,"image":"upload/images/20200302/1583127625555.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"3,4,6,10,1","lastLotteryQiShu":"200620028","lastLotteryTime":1592647320000,"lastModifiedDate":1592647325000,"lastModifiedUser":"sys","listsort":35,"openjgtime":1200,"remark":"全天43期","size":0,"status":1,"tqlasttime":0,"type_id":3,"typename":"山东11选5","version":1304,"waitLotteryQiShu":"200620028"},{"changeVersion":false,"code":"SHxuanwu","createdDate":1528276455000,"createdUser":"sys","currentLotteryQiShu":"200618023","currentLotteryTime":1592469480000,"game":9,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":37,"image":"upload/images/20200302/1583127644759.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"5,1,9,3,4","lastLotteryQiShu":"200620027","lastLotteryTime":1592647305000,"lastModifiedDate":1592647355000,"lastModifiedUser":"sys","listsort":36,"openjgtime":1200,"remark":"全天45期","size":0,"status":1,"tqlasttime":0,"type_id":2,"typename":"上海11选5","version":1298,"waitLotteryQiShu":"200620027"},{"changeVersion":false,"code":"5fen","createdDate":1564199687000,"createdUser":"sys","currentLotteryQiShu":"200618198","currentLotteryTime":1592468990000,"game":9,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":65,"image":"upload/images/20200302/1583127658650.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"4,3,5,9,8","lastLotteryQiShu":"200620217","lastLotteryTime":1592647500000,"lastModifiedDate":1592647502000,"lastModifiedUser":"sys","listsort":37,"openjgtime":300,"remark":"5分钟1期","size":0,"status":1,"tqlasttime":10,"type_id":6,"typename":"5分11选5","version":1186,"waitLotteryQiShu":"200620217"},{"changeVersion":false,"code":"JXxuanwu","createdDate":1528276455000,"createdUser":"sys","currentLotteryQiShu":"200618023","currentLotteryTime":1592470020000,"game":9,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":39,"image":"upload/images/20200302/1583127717819.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"9,6,7,3,5","lastLotteryQiShu":"200620026","lastLotteryTime":1592646677000,"lastModifiedDate":1592646694000,"lastModifiedUser":"sys","listsort":38,"openjgtime":1200,"remark":"全天42期","size":0,"status":1,"tqlasttime":0,"type_id":4,"typename":"江西11选5","version":1302,"waitLotteryQiShu":"200620026"},{"changeVersion":false,"code":"rddd","createdDate":1551236135000,"createdUser":"sys","currentLotteryQiShu":"2006180330","currentLotteryTime":1592468990000,"game":5,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":44,"image":"upload/images/20200302/1583127764232.png","index":0,"isDelete":0,"isHot":1,"isLottery":1,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"8,1,2","lastLotteryQiShu":"2006200361","lastLotteryTime":1592647380000,"lastModifiedDate":1592647382000,"lastModifiedUser":"sys","listsort":39,"openjgtime":180,"remark":"3分钟1期","size":0,"status":1,"tqlasttime":10,"type_id":2,"typename":"瑞典蛋蛋","version":1289,"waitLotteryQiShu":"2006200361"},{"changeVersion":false,"code":"js28","createdDate":1554010252000,"createdUser":"sys","currentLotteryQiShu":"2006180990","currentLotteryTime":1592469000000,"game":5,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":57,"image":"upload/images/20200302/1583127785871.png","index":0,"isDelete":0,"isHot":0,"isLottery":1,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"3,0,2","lastLotteryQiShu":"2006201085","lastLotteryTime":1592647500000,"lastModifiedDate":1592647502000,"lastModifiedUser":"sys","listsort":40,"openjgtime":60,"remark":"1分钟1期","size":0,"status":1,"tqlasttime":0,"type_id":6,"typename":"极速28","version":1256,"waitLotteryQiShu":"2006201085"},{"changeVersion":false,"code":"jnd28","createdDate":1552662316000,"createdUser":"sys","currentLotteryQiShu":"2582921","currentLotteryTime":1592469178000,"game":5,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":56,"image":"upload/images/20200302/1583127806472.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"9,9,0","lastLotteryQiShu":"2583721","lastLotteryTime":1592647462000,"lastModifiedDate":1592647478000,"lastModifiedUser":"sys","listsort":41,"openjgtime":200,"remark":"全天964期","size":0,"status":1,"tqlasttime":0,"type_id":5,"typename":"加拿大28","version":1246,"waitLotteryQiShu":"2583721"},{"changeVersion":false,"code":"HEBK3","createdDate":1528276455000,"createdUser":"sys","currentLotteryQiShu":"200618025","currentLotteryTime":1592470079000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":35,"image":"upload/images/20200302/1583127914345.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"1,5,5","lastLotteryQiShu":"200620028","lastLotteryTime":1592646578000,"lastModifiedDate":1592646626000,"lastModifiedUser":"sys","listsort":42,"openjgtime":1200,"remark":"全天41期","size":0,"status":1,"tqlasttime":1,"type_id":6,"typename":"河北快3","version":1533,"waitLotteryQiShu":"200620028"}]
     * status : success
     */

    private String message;
    private String status;
    private List<OfficialGameClassListBean> officialGameClassList;
    private List<ZhaiyaoListBean> zhaiyaoList;
    private List<ClasstypelistBean> classtypelist;
    private List<GameClasslistBean> gameClasslist;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OfficialGameClassListBean> getOfficialGameClassList() {
        return officialGameClassList;
    }

    public void setOfficialGameClassList(List<OfficialGameClassListBean> officialGameClassList) {
        this.officialGameClassList = officialGameClassList;
    }

    public List<ZhaiyaoListBean> getZhaiyaoList() {
        return zhaiyaoList;
    }

    public void setZhaiyaoList(List<ZhaiyaoListBean> zhaiyaoList) {
        this.zhaiyaoList = zhaiyaoList;
    }

    public List<ClasstypelistBean> getClasstypelist() {
        return classtypelist;
    }

    public void setClasstypelist(List<ClasstypelistBean> classtypelist) {
        this.classtypelist = classtypelist;
    }

    public List<GameClasslistBean> getGameClasslist() {
        return gameClasslist;
    }

    public void setGameClasslist(List<GameClasslistBean> gameClasslist) {
        this.gameClasslist = gameClasslist;
    }

    public static class OfficialGameClassListBean {
        /**
         * game : 4
         * code : shifenliuhe
         * currentLotteryTime : 1592469000000
         * openjgtime : 600
         * iscustom : 1
         * remark : 10分钟1期
         * currentLotteryQiShu : 2006180099
         * gameTouZhuAmount : 0
         * changeVersion : false
         * isopenOffice : 1
         * lastModifiedUser : sys
         * id : 40
         * createdUser : sys
         * image : upload/images/20200302/1583126595473.png
         * waitLotteryQiShu : 2006200108
         * lastLotteryNo : 28,23,49,20,26,12,17
         * lastModifiedDate : 1592647203000
         * isDelete : 0
         * type_id : 2
         * index : 0
         * isLottery : 0
         * isStart : 1
         * lastLotteryTime : 1592647200000
         * tqlasttime : 0
         * version : 1503
         * lastLotteryQiShu : 2006200108
         * createdDate : 1534931922000
         * size : 0
         * gameTouZhuRadio : 0
         * isHot : 1
         * typename : 10分六合彩
         * listsort : 0
         * status : 1
         */

        private int game;
        private String code;
        private long currentLotteryTime;
        private int openjgtime;
        private int iscustom;
        private String remark;
        private String currentLotteryQiShu;
        private int gameTouZhuAmount;
        private boolean changeVersion;
        private int isopenOffice;
        private String lastModifiedUser;
        private String id;
        private String createdUser;
        private String image;
        private String waitLotteryQiShu;
        private String lastLotteryNo;
        private long lastModifiedDate;
        private int isDelete;
        private int type_id;
        private int index;
        private int isLottery;
        private int isStart;
        private long lastLotteryTime;
        private int tqlasttime;
        private int version;
        private String lastLotteryQiShu;
        private long createdDate;
        private int size;
        private int gameTouZhuRadio;
        private int isHot;
        private String typename;
        private int listsort;
        private int status;

        public int getGame() {
            return game;
        }

        public void setGame(int game) {
            this.game = game;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public long getCurrentLotteryTime() {
            return currentLotteryTime;
        }

        public void setCurrentLotteryTime(long currentLotteryTime) {
            this.currentLotteryTime = currentLotteryTime;
        }

        public int getOpenjgtime() {
            return openjgtime;
        }

        public void setOpenjgtime(int openjgtime) {
            this.openjgtime = openjgtime;
        }

        public int getIscustom() {
            return iscustom;
        }

        public void setIscustom(int iscustom) {
            this.iscustom = iscustom;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCurrentLotteryQiShu() {
            return currentLotteryQiShu;
        }

        public void setCurrentLotteryQiShu(String currentLotteryQiShu) {
            this.currentLotteryQiShu = currentLotteryQiShu;
        }

        public int getGameTouZhuAmount() {
            return gameTouZhuAmount;
        }

        public void setGameTouZhuAmount(int gameTouZhuAmount) {
            this.gameTouZhuAmount = gameTouZhuAmount;
        }

        public boolean isChangeVersion() {
            return changeVersion;
        }

        public void setChangeVersion(boolean changeVersion) {
            this.changeVersion = changeVersion;
        }

        public int getIsopenOffice() {
            return isopenOffice;
        }

        public void setIsopenOffice(int isopenOffice) {
            this.isopenOffice = isopenOffice;
        }

        public String getLastModifiedUser() {
            return lastModifiedUser;
        }

        public void setLastModifiedUser(String lastModifiedUser) {
            this.lastModifiedUser = lastModifiedUser;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreatedUser() {
            return createdUser;
        }

        public void setCreatedUser(String createdUser) {
            this.createdUser = createdUser;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getWaitLotteryQiShu() {
            return waitLotteryQiShu;
        }

        public void setWaitLotteryQiShu(String waitLotteryQiShu) {
            this.waitLotteryQiShu = waitLotteryQiShu;
        }

        public String getLastLotteryNo() {
            return lastLotteryNo;
        }

        public void setLastLotteryNo(String lastLotteryNo) {
            this.lastLotteryNo = lastLotteryNo;
        }

        public long getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(long lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getIsLottery() {
            return isLottery;
        }

        public void setIsLottery(int isLottery) {
            this.isLottery = isLottery;
        }

        public int getIsStart() {
            return isStart;
        }

        public void setIsStart(int isStart) {
            this.isStart = isStart;
        }

        public long getLastLotteryTime() {
            return lastLotteryTime;
        }

        public void setLastLotteryTime(long lastLotteryTime) {
            this.lastLotteryTime = lastLotteryTime;
        }

        public int getTqlasttime() {
            return tqlasttime;
        }

        public void setTqlasttime(int tqlasttime) {
            this.tqlasttime = tqlasttime;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getLastLotteryQiShu() {
            return lastLotteryQiShu;
        }

        public void setLastLotteryQiShu(String lastLotteryQiShu) {
            this.lastLotteryQiShu = lastLotteryQiShu;
        }

        public long getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(long createdDate) {
            this.createdDate = createdDate;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getGameTouZhuRadio() {
            return gameTouZhuRadio;
        }

        public void setGameTouZhuRadio(int gameTouZhuRadio) {
            this.gameTouZhuRadio = gameTouZhuRadio;
        }

        public int getIsHot() {
            return isHot;
        }

        public void setIsHot(int isHot) {
            this.isHot = isHot;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public int getListsort() {
            return listsort;
        }

        public void setListsort(int listsort) {
            this.listsort = listsort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    public static class ZhaiyaoListBean {
        /**
         * amount : 0
         * game : 0
         * isDelete : 0
         * isTest : 0
         * orderType : 0
         * play_type : 0
         * price : 0
         * price2 : 0
         * source : 0
         * type : 0
         * typeName : 撤单返还
         * type_id : 0
         */

        private int amount;
        private int game;
        private int isDelete;
        private int isTest;
        private int orderType;
        private int play_type;
        private int price;
        private int price2;
        private int source;
        private int type;
        private String typeName;
        private int type_id;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getGame() {
            return game;
        }

        public void setGame(int game) {
            this.game = game;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public int getIsTest() {
            return isTest;
        }

        public void setIsTest(int isTest) {
            this.isTest = isTest;
        }

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public int getPlay_type() {
            return play_type;
        }

        public void setPlay_type(int play_type) {
            this.play_type = play_type;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getPrice2() {
            return price2;
        }

        public void setPrice2(int price2) {
            this.price2 = price2;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }
    }

    public static class ClasstypelistBean {
        /**
         * gamename : 快3
         * game : 1
         * id : 2
         */

        private String gamename;
        private int game;
        private String id;

        public String getGamename() {
            return gamename;
        }

        public void setGamename(String gamename) {
            this.gamename = gamename;
        }

        public int getGame() {
            return game;
        }

        public void setGame(int game) {
            this.game = game;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class GameClasslistBean implements Serializable {
        /**
         * changeVersion : false
         * code : shifenliuhe
         * createdDate : 1534931922000
         * createdUser : sys
         * currentLotteryQiShu : 2006180099
         * currentLotteryTime : 1592469000000
         * game : 4
         * gameTouZhuAmount : 0
         * gameTouZhuRadio : 0
         * id : 40
         * image : upload/images/20200302/1583126595473.png
         * index : 0
         * isDelete : 0
         * isHot : 1
         * isLottery : 0
         * isStart : 1
         * iscustom : 1
         * isopenOffice : 1
         * lastLotteryNo : 28,23,49,20,26,12,17
         * lastLotteryQiShu : 2006200108
         * lastLotteryTime : 1592647200000
         * lastModifiedDate : 1592647203000
         * lastModifiedUser : sys
         * listsort : 0
         * openjgtime : 600
         * remark : 10分钟1期
         * size : 0
         * status : 1
         * tqlasttime : 0
         * type_id : 2
         * typename : 10分六合彩
         * version : 1503
         * waitLotteryQiShu : 2006200108
         */

        private boolean changeVersion;
        private String code;
        private long createdDate;
        private String createdUser;
        private String currentLotteryQiShu;
        private long currentLotteryTime;
        private int game;
        private int gameTouZhuAmount;
        private int gameTouZhuRadio;
        private String id;
        private String image;
        private int index;
        private int isDelete;
        private int isHot;
        private int isLottery;
        private int isStart;
        private int iscustom;
        private int isopenOffice;
        private String lastLotteryNo;
        private String lastLotteryQiShu;
        private long lastLotteryTime;
        private long lastModifiedDate;
        private String lastModifiedUser;
        private int listsort;
        private int openjgtime;
        private String remark;
        private int size;
        private int status;
        private int tqlasttime;
        private int type_id;
        private String typename;
        private int version;
        private String waitLotteryQiShu;

        public boolean isChangeVersion() {
            return changeVersion;
        }

        public void setChangeVersion(boolean changeVersion) {
            this.changeVersion = changeVersion;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public long getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(long createdDate) {
            this.createdDate = createdDate;
        }

        public String getCreatedUser() {
            return createdUser;
        }

        public void setCreatedUser(String createdUser) {
            this.createdUser = createdUser;
        }

        public String getCurrentLotteryQiShu() {
            return currentLotteryQiShu;
        }

        public void setCurrentLotteryQiShu(String currentLotteryQiShu) {
            this.currentLotteryQiShu = currentLotteryQiShu;
        }

        public long getCurrentLotteryTime() {
            return currentLotteryTime;
        }

        public void setCurrentLotteryTime(long currentLotteryTime) {
            this.currentLotteryTime = currentLotteryTime;
        }

        public int getGame() {
            return game;
        }

        public void setGame(int game) {
            this.game = game;
        }

        public int getGameTouZhuAmount() {
            return gameTouZhuAmount;
        }

        public void setGameTouZhuAmount(int gameTouZhuAmount) {
            this.gameTouZhuAmount = gameTouZhuAmount;
        }

        public int getGameTouZhuRadio() {
            return gameTouZhuRadio;
        }

        public void setGameTouZhuRadio(int gameTouZhuRadio) {
            this.gameTouZhuRadio = gameTouZhuRadio;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public int getIsHot() {
            return isHot;
        }

        public void setIsHot(int isHot) {
            this.isHot = isHot;
        }

        public int getIsLottery() {
            return isLottery;
        }

        public void setIsLottery(int isLottery) {
            this.isLottery = isLottery;
        }

        public int getIsStart() {
            return isStart;
        }

        public void setIsStart(int isStart) {
            this.isStart = isStart;
        }

        public int getIscustom() {
            return iscustom;
        }

        public void setIscustom(int iscustom) {
            this.iscustom = iscustom;
        }

        public int getIsopenOffice() {
            return isopenOffice;
        }

        public void setIsopenOffice(int isopenOffice) {
            this.isopenOffice = isopenOffice;
        }

        public String getLastLotteryNo() {
            return lastLotteryNo;
        }

        public void setLastLotteryNo(String lastLotteryNo) {
            this.lastLotteryNo = lastLotteryNo;
        }

        public String getLastLotteryQiShu() {
            return lastLotteryQiShu;
        }

        public void setLastLotteryQiShu(String lastLotteryQiShu) {
            this.lastLotteryQiShu = lastLotteryQiShu;
        }

        public long getLastLotteryTime() {
            return lastLotteryTime;
        }

        public void setLastLotteryTime(long lastLotteryTime) {
            this.lastLotteryTime = lastLotteryTime;
        }

        public long getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(long lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public String getLastModifiedUser() {
            return lastModifiedUser;
        }

        public void setLastModifiedUser(String lastModifiedUser) {
            this.lastModifiedUser = lastModifiedUser;
        }

        public int getListsort() {
            return listsort;
        }

        public void setListsort(int listsort) {
            this.listsort = listsort;
        }

        public int getOpenjgtime() {
            return openjgtime;
        }

        public void setOpenjgtime(int openjgtime) {
            this.openjgtime = openjgtime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getTqlasttime() {
            return tqlasttime;
        }

        public void setTqlasttime(int tqlasttime) {
            this.tqlasttime = tqlasttime;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getWaitLotteryQiShu() {
            return waitLotteryQiShu;
        }

        public void setWaitLotteryQiShu(String waitLotteryQiShu) {
            this.waitLotteryQiShu = waitLotteryQiShu;
        }
    }
}
