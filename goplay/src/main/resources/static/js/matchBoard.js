$(function(){
    let pageData;

    let printPage = function(){
        $.ajax({
            url: "/findMatch",
            success: function(data){
                let mb = data.content;
                console.log(mb)


                $.each(mb, function(){
                    let homeClubName = "";
                    let awayClubName = "";
                    //등록팀 이름 가져오기
                    $.ajax({
                        url:"/findClub/"+this.homeClub,
                        success: function(data){
                            homeClubName = data[0].cname;
                        },
                        async: false
                    });

                    //신청팀 이름 가져오기
                    $.ajax({
                        url:"/findClub/"+this.awayClub,
                        success: function(data){
                            awayClubName = data[0].cname;
                        },
                        async: false
                    });

                    // 날짜 변환
                    //2022-06-11T00:00:00
                    let mbDateData = this.mbDate;
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

                    //매치 전적
                    let homeRecordData;
                    let awayRecordData;
                    $.ajax({
                        url:"/matchRecord/"+this.homeClub,
                        success: function(data){
                            homeRecordData = data[0];
                        },
                        async: false
                    });
                    $.ajax({
                        url:"/matchRecord/"+this.awayClub,
                        success: function(data){
                            awayRecordData = data[0];
                        },
                        async: false
                    });
                    let homeRecord = homeRecordData.win+"승 "+homeRecordData.draw+"무 "+homeRecordData.lose+"패";
                    let awayRecord = awayRecordData.win+"승 "+awayRecordData.draw+"무 "+awayRecordData.lose+"패"
                    
                    if(this.mbStat == "대기"){
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
                    }else if(this.mbStat == "성사"){
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
                    }else if(this.mbStat == "종료"){
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
});