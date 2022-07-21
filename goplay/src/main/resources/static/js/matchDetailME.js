$(function(){
    let mbNo = $('#mbNo').val();
    let loginID;
    let role;
    let mbStat;
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

    // 경기정보 조회
    $.ajax({
        url:"/detailMatch/"+mbNo,
        success: function(data){
            let m = data[0]
            console.log(m);
            mbDate = m.mbDate;
            mbType = m.mbType;
            mbLoc1 = m.mbLoc1;
            mbLoc2 = m.mbLoc2;
            mbStadium = m.mbStadium;
            mbFee = m.mbFee;
            homeUcolor = m.homeUcolor;
            homeLevel = m.homeLevel;
            homeSay = m.homeSay;
            awayClub = m.awayClub;
            awayUcolor = m.awayUcolor;
            awayLevel = m.awayLevel;
            awaySay = m.awaySay;
        },
        async:false
    });

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
            homeClub = data[0].homeClub;
            mbStat = data[0].mbStat;
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
    let printMatchInfo = function(){
        $.ajax({
            url:"/detailMatch/"+mbNo,
            success: function(data){
                let m = data[0]
                let homeClubName = getClubName(m.homeClub);
                let awayClubName = getClubName(m.awayClub);

                if(m.mbStat == "성사"){
                    $('#pageTitle').html("성사된 매치");
                }else if(m.mbStat == "종료"){
                    $('#pageTitle').html("종료된 매치");
                }

                let cName = getClubName(m.homeClub);
                $("#clubBtn").html(cName);

                let matchRecord = getMatchRecord(m.homeClub);
                $("#matchRecord").html(matchRecord);

                let mbDate = printDate(m.mbDate)
                $("#mbDate").append($('<td></td>').html(mbDate));

                $("#mbType").append($('<td></td>').html(m.mbType));
                $("#mbLoc").append($('<td></td>').html(m.mbLoc1+" "+m.mbLoc2));
                $("#mbStadium").append($('<td></td>').html(m.mbStadium));
                $("#mbFee").append($('<td></td>').html(m.mbFee));
                $("#homeUcolor").append($('<td></td>')
                    .append($('<span></span>').html(homeClubName+": "+m.homeUcolor))
                    .append($('<span class="pl-5"></span>').html(awayClubName+": "+m.awayUcolor)));
                $("#homeLevel").append($('<td></td>').html(m.homeLevel));
                $("#homeSay").append($('<td></td>').html(m.homeSay));
            }
        });
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

    //동호회명 조회 function
    let getClubName = function(cNo){
        let cName = "";
        $.ajax({
            url:"/findClub/"+cNo,
            success: function(data){
                cName = data[0].cname;
            },
            async: false
        });
        return cName;
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

    // 매치정보 출력 function
    let printPage = function(){
        $.ajax({
            url: "/detailMatch/"+mbNo,
            success: function(data) {
                let mb = data[0]
                console.log(mb);
                let homeClubName;
                let awayClubName;
                let homeRecord;
                let awayRecord;
                let mbDate;

                if(mb.mbStat == "성사") {
                    homeClubName = getClubName(mb.homeClub)
                    awayClubName = getClubName(mb.awayClub)
                    homeRecord = getMatchRecord(mb.homeClub);
                    awayRecord = getMatchRecord(mb.awayClub);
                    let searchForm_matched =
                        $("<div></div>").addClass("box-body p-2")
                            .append($("<div></div>").addClass("row")
                                .append($("<div></div>").addClass("col-md-12 bg-light")
                                    .append($("<div></div>").addClass("row mt-2")
                                        // 홈 클럽 정보
                                        .append($("<div></div>").addClass("col-md-3")
                                            .append($("<img></img>").addClass("rounded").attr("width", "100%").attr("src", "../img/clogo.png"),
                                                $("<button></button>").attr("type", "button").addClass("btn btn-outline-primary btn-block pt-2").addClass("cNo", mb.homeClub).html("팀 정보")))
                                        // 매치 정보
                                        .append($("<div></div>").addClass("col-md-6")
                                            .append($("<div class='row'><div/>")
                                                .append($("<div class='col-md-4'></div>")
                                                    .append($("<div class=\"box-title\"></div>")
                                                        .append($("<h6></h6>").html(homeClubName)))
                                                    .append($("<ul class='list-group list-group-flush'></ul>")
                                                        .append($("<li class=\"d-flex flex-row text-dark\"></li>")
                                                            .append($("<i class=\"feather-award mr-2 text-dark\"></i>").html(" " + homeRecord)))))
                                                .append($("<div class=\"col-md-4\"></div>")
                                                    .append($("<div class=\"box-title d-flex justify-content-center\"></div>")
                                                        .append($('<h4 class="mb-3 font-weight-bold"></h4>').html(" 경기예정 ")))
                                                    .append($('<div class="d-flex justify-content-center"></div>')
                                                        .append($('<ul class="list-group list-group-flush d-flex"></ul>')
                                                            .append($('<h1 class="mt-4">VS</h1>')))))
                                                .append($("<div class=\"col-md-4\"></div>")
                                                    .append($("<div class=\"box-title d-flex justify-content-end\"></div>")
                                                        .append($("<h6></h6>").html(awayClubName)))
                                                    .append($("<div class=\"d-flex justify-content-end\">")
                                                        .append($("<ul class=\"list-group list-group-flush\">")
                                                            .append($("<li class=\"d-flex flex-row text-dark\">")
                                                                .append($("<i class=\"feather-award mr-2 text-dark\">").html(" " + awayRecord))))))))
                                        .append($("<div></div>").addClass("col-md-3")
                                            .append($("<img></img>").addClass("rounded").attr("width", "100%").attr("src", "../img/clogo.png"),
                                                $("<button></button>").attr("type", "button").addClass("btn btn-outline-primary btn-block pt-2").addClass("cNo", mb.awayClub).html("팀 정보"))))));

                    $("#matchInfoContainer").append(searchForm_matched);
                }else if(mb.mbStat == "종료"){
                    homeClubName = getClubName(mb.homeClub)
                    awayClubName = getClubName(mb.awayClub)
                    homeRecord = getMatchRecord(mb.homeClub);
                    awayRecord = getMatchRecord(mb.awayClub);
                    let searchForm_end =
                        $("<div></div>").addClass("box-body p-2")
                            .append($("<div></div>").addClass("row")
                                .append($("<div></div>").addClass("col-md-12 bg-light")
                                    .append($("<div></div>").addClass("row mt-2")
                                        // 홈 클럽 정보
                                        .append($("<div></div>").addClass("col-md-3")
                                            .append($("<img></img>").addClass("rounded").attr("width", "100%").attr("src", "../img/clogo.png"),
                                                $("<button></button>").attr("type", "button").addClass("btn btn-outline-primary btn-block pt-2").addClass("cNo", mb.homeClub).html("팀 정보")))
                                        // 매치 정보
                                        .append($("<div></div>").addClass("col-md-6")
                                            .append($("<div class='row'><div/>")
                                                .append($("<div class='col-md-4'></div>")
                                                    .append($("<div class=\"box-title\"></div>")
                                                        .append($("<h6></h6>").html(homeClubName)))
                                                    .append($("<ul class='list-group list-group-flush'></ul>")
                                                        .append($("<li class=\"d-flex flex-row text-dark\"></li>")
                                                            .append($("<i class=\"feather-award mr-2 text-dark\"></i>").html(" " + homeRecord)))))
                                                .append($("<div class=\"col-md-4\"></div>")
                                                    .append($("<div class=\"box-title d-flex justify-content-center\"></div>")
                                                        .append($('<h4 class="mb-3 font-weight-bold"></h4>').html(" 경기종료 ")))
                                                    .append($('<div class="d-flex justify-content-center"></div>')
                                                        .append($('<ul class="list-group list-group-flush d-flex"></ul>')
                                                            .append($('<h1 class="mt-4"></h1>').html(mb.hscore+" : "+mb.ascore)))))
                                                .append($("<div class=\"col-md-4\"></div>")
                                                    .append($("<div class=\"box-title d-flex justify-content-end\"></div>")
                                                        .append($("<h6></h6>").html(awayClubName)))
                                                    .append($("<div class=\"d-flex justify-content-end\">")
                                                        .append($("<ul class=\"list-group list-group-flush\">")
                                                            .append($("<li class=\"d-flex flex-row text-dark\">")
                                                                .append($("<i class=\"feather-award mr-2 text-dark\">").html(" " + awayRecord))))))))
                                        .append($("<div></div>").addClass("col-md-3")
                                            .append($("<img></img>").addClass("rounded").attr("width", "100%").attr("src", "../img/clogo.png"),
                                                $("<button></button>").attr("type", "button").addClass("btn btn-outline-primary btn-block pt-2").addClass("cNo", mb.awayClub).html("팀 정보"))))));

                    $("#matchInfoContainer").append(searchForm_end);
                }
            }
        });
    }

    let printScoreBtn = function(){
        if(role == "host" && mbStat == "성사"){
            $('#scoreInput')
                .append($('<h6 class="font-weight-bold mt-2">경기스코어</h6>&nbsp;&nbsp;\n' +
                    '                        <input type="text" name="hScore" class="form-control" style="width:50px;">&nbsp;&nbsp;:&nbsp;&nbsp;\n' +
                    '                        <input type="text" name="aScore" class="form-control" style="width:50px;">&nbsp;&nbsp;&nbsp;&nbsp;\n' +
                    '                        <button class="btn btn-primary mr-2" id="endGame">경기 종료</button>'));
        }
    }
    printPage();
    printMatchInfo();
    printScoreBtn();

    // 경기 종료 시 이벤트
    $(document).on('click', '#endGame', function(){
        hScore = $('input[name=hScore]').val();
        aScore = $('input[name=aScore]').val();
        console.log(hScore+":"+aScore);
        if(hScore!="" && aScore!=""){
            let gameData = {
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
                "mbStat": "종료",
                "awayClub": awayClub,
                "awayUcolor": awayUcolor,
                "awayLevel": awayLevel,
                "awaySay": awaySay,
                "hScore": hScore,
                "aScore": aScore
            }

            $.ajax({
                url:"/saveMatchBoard",
                type: "POST",
                data: JSON.stringify(gameData),
                contentType: "application/json",
                beforeSend: function (jqXHR, settings) {
                    let header = $("meta[name='_csrf_header']").attr("content");
                    let token = $("meta[name='_csrf']").attr("content");
                    jqXHR.setRequestHeader(header, token);
                },
                success: function(){
                    // location.href = "/matchDetailME/"+mbNo;
                    console.log("성공");
                }
            });

            let scoreData = {
                "mbNo": mbNo,
                "homeClub": homeClub,
                "awayClub": awayClub,
                "hScore": hScore,
                "aScore": aScore
            }

            $.ajax({
                url:"/insertScore",
                type: "POST",
                data: JSON.stringify(scoreData),
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
            alert("경기스코어를 입력해 주세요");
        }

    });
});