$(function(){
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
        let record = recordData.win+"승 "+recordData.draw+"무 "+recordData.lose+"패";
        return record;
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

    //시도 지역선택 드롭다운 리스트
    $.ajax({
        url:"/listCity",
        success: function(data){
            $.each(data,function(){
                let option = $("<option></option>");
                option.html(this.acName);
                option.attr("value",this.acName);
                $("#mbLoc1").append(option);
            });
        }
    });

    //시도 지역분류 선택 시 세부지역 출력
    $(document).on("change", "#loc1", function (){
        $("#mbLoc2").empty();
        let acName = $(this).val()
        $.ajax({
            url:"/listDistrict/"+acName,
            success: function(data) {
                let district = data[0].address_district;
                $.each(district,function(){
                    let option = $("<option></option>");
                    option.html(this.adName);
                    option.attr("value",this.adName);
                    $("#mbLoc2").append(option);
                });
            }
        });
    });

    //시도 지역선택 초기 값에 따라 서울특별시 세부지역 표시
    $.ajax({
        url:"/listDistrict/"+"서울특별시",
        success: function(data) {
            let district = data[0].address_district;
            $.each(district,function(){
                let option = $("<option></option>");
                option.html(this.adName);
                option.attr("value",this.adName);
                $("#mbLoc2").append(option);
            });
        }
    });

    // 검색조건 전역 변수
    let mbDate=null;
    let mbType=null;
    let mbLoc1=null;
    let mbLoc2=null;
    let mbStat_wait=null;
    let mbStat_matched=null;
    let mbStat_end=null;
    let size = 6;
    let page = 0;



    // 검색목록 출력 function
    let printPage = function(){
        let searchCondition = {
            "mbDate" :mbDate,
            "mbType" : mbType,
            "mbLoc1" : mbLoc1,
            "mbLoc2" : mbLoc2,
            "mbStat_wait" : mbStat_wait,
            "mbStat_matched" : mbStat_matched,
            "mbStat_end" : mbStat_end,
            "size" : size,
            "page" : page};

        $.ajax({
            url: "/findMatch",
            type:"POST",
            data: JSON.stringify(searchCondition),
            contentType: "application/json",
            beforeSend: function (jqXHR, settings) {
                let header = $("meta[name='_csrf_header']").attr("content");
                let token = $("meta[name='_csrf']").attr("content");
                jqXHR.setRequestHeader(header, token);
            },
            error: function(){
                console.log("error")
            },
            success: function(data){
                let mb = data.content;
                console.log(mb)
                $.each(mb, function(){
                    let mbStat = this.mbStat;
                    let homeClubName;
                    let awayClubName;
                    let homeRecord;
                    let awayRecord;
                    let mbDate;

                    if(mbStat == '대기'){
                        homeClubName = getClubName(this.homeClub)
                        homeRecord = getMatchRecord(this.homeClub);
                        mbDate = printDate(this.mbDate);

                        let searchForm_wait =
                            $("<div></div>").addClass("box-body p-2")
                                .append($("<div></div>").addClass("row")
                                    .append($("<div></div>").addClass("col-md-12 bg-light")
                                        .append($("<div></div>").addClass("row mt-2")
                                            // 홈 클럽 정보
                                            .append($("<div></div>").addClass("col-md-2")
                                                .append($("<img></img>").addClass("rounded").attr("width", "100%").attr("src","img/clogo.png"),
                                                    $("<button></button>").attr("type", "button").addClass("btn btn-outline-primary btn-block pt-2").addClass("cNo", this.homeClub).html("팀 정보")))
                                            // 매치 정보
                                            .append($("<div></div>").addClass("col-md-8")
                                                .append($("<div class='row'><div/>")
                                                    .append($("<div class='col-md-3'></div>")
                                                        .append($("<div class=\"box-title\"></div>")
                                                            .append($("<h6></h6>").html(homeClubName)))
                                                        .append($("<ul class='list-group list-group-flush'></ul>")
                                                            .append($("<li class=\"d-flex flex-row text-dark\"></li>")
                                                                .append($("<i class=\"feather-award mr-2 text-dark\"></i>").html(" "+homeRecord)))))
                                                    .append($("<div class=\"col-md-6\"></div>")
                                                        .append($("<div class=\"box-title d-flex justify-content-center\"></div>")
                                                            .append($("<h3 class=\"mb-3 font-weight-bold\"></h3>").html(" 매칭중 ")))
                                                        .append($('<div class="d-flex justify-content-center"></div>')
                                                            .append($('<ul class="list-group list-group-flush d-flex"></ul>')
                                                                .append($('<i class="feather-target mr-2 mb-2 text-dark"></i>').html(" "+this.mbType))
                                                                .append($('<i class="feather-calendar mr-2 mb-2 text-dark"></i>').html(" "+mbDate))
                                                                .append($('<i class="feather-clock mr-2 mb-2 text-dark"></i>').html(" "+this.mbLoc1+" "+this.mbLoc2))
                                                                .append($('<i class="feather-map-pin mr-2 mb-2 text-dark"></i>').html(" "+this.mbStadium))
                                                                .append($('<button class="btn btn-primary mr-auto ml-auto" style="height: 30px; width: 135px">매치 신청하기</button>').addClass("mbNo", this.mb_no)))))
                                                    .append($('<div class=\"col-md-3\"></div>')
                                                        .append($("<div class=\"box-title d-flex justify-content-end\"></div>")
                                                            .append($("<h6></h6>").html(" 매칭 대기 중 ")))
                                                        .append($("<div class=\"d-flex justify-content-end\">")
                                                            .append($("<ul class=\"list-group list-group-flush\">")
                                                                .append($("<li class=\"d-flex flex-row text-dark\">")))))))
                                            .append($("<div></div>").addClass("col-md-2")
                                                .append($("<img></img>").addClass("rounded").attr("width", "100%").attr("src","img/clogo.png"),
                                                    $("<button></button>").attr("type", "button").addClass("btn btn-outline-primary btn-block pt-2").addClass("cNo", this.awayClub).html("팀 정보"))))));

                        $("#matchContainer").append(searchForm_wait);
                    }else if(mbStat == '성사'){
                        homeClubName = getClubName(this.homeClub)
                        awayClubName = getClubName(this.awayClub)
                        homeRecord = getMatchRecord(this.homeClub);
                        awayRecord = getMatchRecord(this.awayClub);
                        mbDate = printDate(this.mbDate);

                        let searchForm_matched =
                            $("<div></div>").addClass("box-body p-2")
                                .append($("<div></div>").addClass("row")
                                    .append($("<div></div>").addClass("col-md-12 bg-light")
                                        .append($("<div></div>").addClass("row mt-2")
                                            // 홈 클럽 정보
                                            .append($("<div></div>").addClass("col-md-2")
                                                .append($("<img></img>").addClass("rounded").attr("width", "100%").attr("src","img/clogo.png"),
                                                    $("<button></button>").attr("type", "button").addClass("btn btn-outline-primary btn-block pt-2").addClass("cNo", this.homeClub).html("팀 정보")))
                                            // 매치 정보
                                            .append($("<div></div>").addClass("col-md-8")
                                                .append($("<div class='row'><div/>")
                                                    .append($("<div class='col-md-3'></div>")
                                                        .append($("<div class=\"box-title\"></div>")
                                                            .append($("<h6></h6>").html(homeClubName)))
                                                        .append($("<ul class='list-group list-group-flush'></ul>")
                                                            .append($("<li class=\"d-flex flex-row text-dark\"></li>")
                                                                .append($("<i class=\"feather-award mr-2 text-dark\"></i>").html(" "+homeRecord)))))
                                                    .append($("<div class=\"col-md-6\"></div>")
                                                        .append($("<div class=\"box-title d-flex justify-content-center\"></div>")
                                                            .append($('<h3 class="mb-3 font-weight-bold"></h3>').html(" 경기예정 ")))
                                                        .append($('<div class="d-flex justify-content-center"></div>')
                                                            .append($('<ul class="list-group list-group-flush d-flex"></ul>')
                                                                .append($('<i class="feather-target mr-2 mb-2 text-dark"></i>').html(" "+this.mbType))
                                                                .append($('<i class="feather-calendar mr-2 mb-2 text-dark"></i>').html(" "+mbDate))
                                                                .append($('<i class="feather-clock mr-2 mb-2 text-dark"></i>').html(" "+this.mbLoc1+" "+this.mbLoc2))
                                                                .append($('<i class="feather-map-pin mr-2 mb-2 text-dark"></i>').html(" "+this.mbStadium))
                                                                .append($('<button class="btn btn-primary mr-auto ml-auto" style="height: 30px; width: 135px">경기정보 상세보기</button>').addClass("mbNo", this.mb_no)))))
                                                    .append($("<div class=\"col-md-3\"></div>")
                                                        .append($("<div class=\"box-title d-flex justify-content-end\"></div>")
                                                            .append($("<h6></h6>").html(awayClubName)))
                                                        .append($("<div class=\"d-flex justify-content-end\">")
                                                            .append($("<ul class=\"list-group list-group-flush\">")
                                                                .append($("<li class=\"d-flex flex-row text-dark\">")
                                                                    .append($("<i class=\"feather-award mr-2 text-dark\">").html(" "+awayRecord))))))))
                                            .append($("<div></div>").addClass("col-md-2")
                                                .append($("<img></img>").addClass("rounded").attr("width", "100%").attr("src","img/clogo.png"),
                                                    $("<button></button>").attr("type", "button").addClass("btn btn-outline-primary btn-block pt-2").addClass("cNo", this.awayClub).html("팀 정보"))))));

                        $("#matchContainer").append(searchForm_matched);
                    }else if(mbStat == "종료"){
                        homeClubName = getClubName(this.homeClub)
                        awayClubName = getClubName(this.awayClub)
                        homeRecord = getMatchRecord(this.homeClub);
                        awayRecord = getMatchRecord(this.awayClub);
                        mbDate = printDate(this.mbDate);
                        let searchForm_end =
                            $("<div></div>").addClass("box-body p-2")
                                .append($("<div></div>").addClass("row")
                                    .append($("<div></div>").addClass("col-md-12 bg-light")
                                        .append($("<div></div>").addClass("row mt-2")
                                            // 홈 클럽 정보
                                            .append($("<div></div>").addClass("col-md-2")
                                                .append($("<img></img>").addClass("rounded").attr("width", "100%").attr("src","img/clogo.png"),
                                                    $("<button></button>").attr("type", "button").addClass("btn btn-outline-primary btn-block pt-2").addClass("cNo", this.homeClub).html("팀 정보")))
                                            // 매치 정보
                                            .append($("<div></div>").addClass("col-md-8")
                                                .append($("<div class='row'><div/>")
                                                    .append($("<div class='col-md-3'></div>")
                                                        .append($("<div class=\"box-title\"></div>")
                                                            .append($("<h6></h6>").html(homeClubName)))
                                                        .append($("<ul class='list-group list-group-flush'></ul>")
                                                            .append($("<li class=\"d-flex flex-row text-dark\"></li>")
                                                                .append($("<i class=\"feather-award mr-2 text-dark\"></i>").html(" "+homeRecord)))))
                                                    .append($("<div class=\"col-md-6\"></div>")
                                                        .append($("<div class=\"box-title d-flex justify-content-center\"></div>")
                                                            .append($("<h1></h1>").html(this.hscore+" : "+this.hscore)))
                                                        .append($('<div class="d-flex justify-content-center"></div>')
                                                            .append($('<ul class="list-group list-group-flush d-flex"></ul>')
                                                                .append($('<i class="feather-target mr-2 mb-2 text-dark"></i>').html(" "+this.mbType))
                                                                .append($('<i class="feather-calendar mr-2 mb-2 text-dark"></i>').html(" "+mbDate))
                                                                .append($('<i class="feather-clock mr-2 mb-2 text-dark"></i>').html(" "+this.mbLoc1+" "+this.mbLoc2))
                                                                .append($('<i class="feather-map-pin mr-2 mb-2 text-dark"></i>').html(" "+this.mbStadium))
                                                                .append($('<button class="btn btn-primary mr-auto ml-auto" style="height: 30px; width: 135px">경기결과 상세보기</button>').addClass("mbNo", this.mb_no)))))
                                                    .append($("<div class=\"col-md-3\"></div>")
                                                        .append($("<div class=\"box-title d-flex justify-content-end\"></div>")
                                                            .append($("<h6></h6>").html(awayClubName)))
                                                        .append($("<div class=\"d-flex justify-content-end\">")
                                                            .append($("<ul class=\"list-group list-group-flush\">")
                                                                .append($("<li class=\"d-flex flex-row text-dark\">")
                                                                    .append($("<i class=\"feather-award mr-2 text-dark\">").html(" "+awayRecord))))))))
                                            // 어웨이 클럽 정보
                                            .append($("<div></div>").addClass("col-md-2")
                                                .append($("<img></img>").addClass("rounded").attr("width", "100%").attr("src","img/clogo.png"),
                                                    $("<button></button>").attr("type", "button").addClass("btn btn-outline-primary btn-block pt-2").addClass("cNo", this.awayClub).html("팀 정보"))))));
                        $("#matchContainer").append(searchForm_end);
                    }
                });
            }
        });
    }
    printPage();

    // 종목선택 시 이벤트
    $(document).on("change", "input[name='mbType']", function(){
        $("#matchContainer").empty();
        console.log($(this).val())
        mbType = $(this).val();
        printPage();
    });

    // 지역 선택 시 이벤트
    $(document).on("change", "#mbLoc1", function(){
        $("#matchContainer").empty();
        console.log($(this).val())
        mbLoc1 = $(this).val();
        printPage();
    });

    // 세부지역 선택 시 이벤트
    $(document).on("change", "#mbLoc2", function(){
        $("#matchContainer").empty();
        console.log($(this).val());
        mbLoc2 = $(this).val();
        printPage();
    });

    //매치상태 선택 시 이벤트
    $(document).on("change", "input[name='mbStat']", function(){
        $("#matchContainer").empty();
        console.log($(this).is(":checked"))
        if($(this).is(":checked")) {
            if ($(this).val() == "대기") {
                mbStat_wait = $(this).val();
            } else if ($(this).val() == "성사") {
                mbStat_matched = $(this).val();
            } else if ($(this).val() == "종료") {
                mbStat_end = $(this).val();
            }
        }else{
            if ($(this).val() == "대기") {
                mbStat_wait=null;
            } else if ($(this).val() == "성사") {
                mbStat_matched=null;
            } else if ($(this).val() == "종료") {
                mbStat_end=null;
            }
        }
        printPage();
    });
});