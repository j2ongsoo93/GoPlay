$(function(){
    let mbNo = $('#mbNo').val();
    let loginID;
    let role;
    let homeClub = null;
    let awayClub = null;
    let mbDate = null;
    let mbType = null;
    let mbLoc1 = null;
    let mbLoc2 = null;
    let mbStadium = null;
    let mbFee = null;
    let homeUcolor = null;
    let homeLevel = null;
    let homeSay = null;
    let hScore = null;
    let aScore = null;
    let awayUcolor = null;
    let awayLevel = null;
    let awaySay = null;


    //로그인한 아이디 조회
    $.ajax({
        url:"/loginmember",
        success:function(data){
            loginID = data;
        },
        async:false
    });

    //해당 매치의 등록팀 조회
    $.ajax({
        url: "/detailMatch/"+mbNo,
        success: function(data){
            homeClub = data[0].homeClub
        },
        async: false
    });

    //회원 역할 구분 user: 동호회 회장이 아닌 회원, host: 해당 매치를 동호회장, manager: 타 동호회 동호회장
    $.ajax({
       url: "/findClubById/"+loginID,
       success: function(data) {
           if(data.length == 0){
               role = "user"
           }else{
               let cNo = data[0].cno;
               if (homeClub == cNo) {
                   role = "host";
               }else{
                   role = "manager"
               }
           }
       },
       async: false
    });
    console.log(role)

    // 경기정보 출력
    $.ajax({
        url:"/detailMatch/"+mbNo,
        success: function(data){
            let m = data[0]
            console.log(m);

            let cName = getClubName(m.homeClub);
            $("#clubBtn").html(cName);

            let matchRecord = getMatchRecord(m.homeClub);
            $("#matchRecord").html(matchRecord);

            let mbDateTime = printDate(m.mbDate)
            $("#mbDate").append($('<td></td>').html(mbDateTime));

            $("#mbType").append($('<td></td>').html(m.mbType));
            $("#mbLoc").append($('<td></td>').html(m.mbLoc1+" "+m.mbLoc2));
            $("#mbStadium").append($('<td></td>').html(m.mbStadium));
            $("#mbFee").append($('<td></td>').html(m.mbFee));
            $("#homeUcolor").append($('<td></td>').html(m.homeUcolor));
            $("#homeLevel").append($('<td></td>').html(m.homeLevel));
            $("#homeSay").append($('<td></td>').html(m.homeSay));

            mbDate = m.mbDate;
            mbType = m.mbType;
            mbLoc1 = m.mbLoc1;
            mbLoc2 = m.mbLoc2;
            mbStadium = m.mbStadium;
            mbFee = m.mbFee;
            homeUcolor = m.homeUcolor;
            homeLevel = m.homeLevel;
            homeSay = m.homeSay;

        }
    });

    //버튼 출력
    if(role == "host"){
        $("#btnContainer")
            .append($('<button>수정</button>').addClass("btn btn-primary").attr("mbNo", mbNo).attr("id", "updateMatch"))
            .append($('<span>&nbsp;&nbsp;</span>'))
            .append($('<button>삭제</button>').addClass("btn btn-primary").attr("mbNo", mbNo).attr("id", "deleteMatch"));

        $("#btnOffer")
            .append($('<button>매치 수락</button>').addClass("btn btn-primary mr-3").attr("id", "acceptOffer"))
    }else if(role == "manager"){
        $("#btnOffer")
            .append($('<button>매치 신청</button>').addClass("btn btn-primary mr-3").attr("mbNo", mbNo).attr("id", "applyMatch"))
    }

    // 매치 신청정보 출력
    $.ajax({
        url:"/listMatchOffer/"+mbNo,
        success: function(data){
            console.log("매치 신청현황: "+data);
            $.each(data, function(){
                let tr = $('<tr></tr>');
                let td1 = $('<td><input id="'+this.moNo+'" name="matchOffer" value="'+this.moNo+'" type="radio"></td>')
                let td2 = $('<td></td>').html(getClubName(this.cno));
                let td3 = $('<td></td>').html(this.moSay);
                let td4 = $('<td></td>').html(printDate(this.moDate));
                $(tr).append(td1, td2, td3, td4);
                $("#matchOfferContainer").append(tr);
            });
        }
    });

    //동호회명 조회 function
    let getClubName = function(cNo){
        let cName = "";
        $.ajax({
            url:"/findClub/"+cNo,
            success: function(data){
                console.log(data)
                cName = data[0].cname;
            },
            async: false
        });
        return cName;
    }

    //매치 전적조회 function
    let getMatchRecord = function(cNo){
        let recordData;

        $.ajax({
            url:"/matchRecord/"+cNo,
            success: function(data){
                recordData = data[0];
            },
            async: false
        });
        if(recordData.win!=null){
            let record = recordData.win+"승 "+recordData.draw+"무 "+recordData.lose+"패";
            return record;
        }else{
            return "0승 0무 0패"
        }
    }

    // 날짜 변환 function
    let printDate = function(datetime){
        //2022-06-11T00:00:00
        let mbDateData = datetime;
        let date = new Date(mbDateData.substring(0,10));
        // 년 월 일
        let year = date.getFullYear();
        let month = date.getMonth()+1;
        let day = date.getDate();
        //요일
        let week = ['일','월','화','수','목','금','토']
        let dayOfWeek = week[date.getDay()];
        //시간
        let time = mbDateData.substring(11,16);
        let mbDate = year+'년 '+month+'월 '+day+'일 ('+dayOfWeek+') '+time;
        return mbDate;
    }

    //수정버튼 클릭 이벤트
    $(document).on("click", "#updateMatch", function(){
       location.href = "/editMatch/"+mbNo
    });

    //삭제버튼 클릭 이벤트
    $(document).on("click", "#deleteMatch", function(){

    });

    //매치신청 클릭 이벤트
    $(document).on("click", "#applyMatch", function(){
        location.href = "/matchOffer/"+mbNo
    });

    //매치수락 클릭 이벤트
    $(document).on("click", "#acceptOffer", function(){
        let moNo = $('input[name=matchOffer]:checked').val();
        if(moNo != null){
            $.ajax({
                url: "/findMatchOffer/"+moNo,
                success: function(data){
                    let mo = data[0];
                    awayUcolor = mo.moUcolor;
                    awayLevel = mo.moLevel;
                    awaySay = mo.moSay;
                    awayClub = mo.cno;
                },
                async: false
            });

            let moData = {
                "mbNo": mbNo,
                "homeClub": homeClub,
                "mbDate": mbDate,
                "mbType": mbType,
                "mbLoc1": mbLoc1,
                "mbLoc2": mbLoc2,
                "mbStadium": mbStadium,
                "mbFee": mbFee,
                "homeUcolor": homeUcolor,
                "homeLevel": homeLevel,
                "homeSay": homeSay,
                "mbStat": "성사",
                "mbNo": mbNo,
                "awayClub": awayClub,
                "awayUcolor": awayUcolor,
                "awayLevel": awayLevel,
                "awaySay": awaySay,
            }

            $.ajax({
                url:"/saveMatchBoard",
                type: "POST",
                data: JSON.stringify(moData),
                contentType: "application/json",
                beforeSend: function (jqXHR, settings) {
                    let header = $("meta[name='_csrf_header']").attr("content");
                    let token = $("meta[name='_csrf']").attr("content");
                    jqXHR.setRequestHeader(header, token);
                },
                success: function(){
                    location.href = "/matchDetailME/"+mbNo;
                    console.log("성공");
                }
            });
        }else{
            alert("상대할 팀을 선택해 주세요");
        }


    });
});