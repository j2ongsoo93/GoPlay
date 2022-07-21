$(function() {
    //TODO: 입력값 validation

    let mbNo = $('#mbNo').val();
    let loginID;
    let role;
    let homeClub = null;
    let awayClub = null;
    let mbDate = null;
    let mbTime_hh = null;
    let mbTime_mm = null;
    let mbType = null;
    let mbLoc1 = null;
    let mbLoc2 = null;
    let mbStadium = null;
    let mbFee = null;
    let homeUcolor = null;
    let awayUcolor = null;
    let homeLevel = null;
    let awayLevel = null;
    let homeSay = null;
    let awaySay = null;
    let hScore = null;
    let aScore = null;
    let mbStat = null;

    //로그인한 아이디 조회
    $.ajax({
        url:"/loginmember",
        success:function(data){
            loginID = data;
        },
        async:false
    });

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

    //회원 역할 구분 user: 동호회 회장이 아닌 회원, host: 해당 매치를 동호회장, manager: 타 동호회 동호회장
    $.ajax({
        url: "/findClubById/hippo123",
        success: function(data) {
            if(data.length == 0){
                role = "user"
            }else{
                role = "manager"
                awayClub = data[0].cno;
            }
        },
        async: false
    });
    console.log(role)

    //시도 지역선택 드롭다운 리스트
    $.ajax({
        url: "/listCity",
        success: function (data) {
            $.each(data, function () {
                let option = $("<option></option>");
                option.html(this.acName);
                option.attr("value", this.acName);
                option.attr("acNo", this.acNo);
                option.attr("id", this.acName);
                $("#mbLoc1").append(option);
            });
        }
    });

    // 지역 선택 시 이벤트
    $(document).on("change", "#mbLoc1", function () {
        $("#matchContainer").empty();
        $("#mbLoc2").empty();
        let mbLoc1 = $(this).val();
        //시도 지역분류 선택 시 세부지역 출력
        $.ajax({
            url: "/listDistrict/" + mbLoc1,
            success: function (data) {
                $.each(data, function () {
                    let option = $("<option></option>");
                    option.html(this.adName);
                    option.attr("value", this.adName);
                    option.attr("id", this.adName);
                    $("#mbLoc2").append(option);
                });
            }
        });
    });

    //시도 지역선택 초기 값에 따라 서울특별시 세부지역 표시
    $.ajax({
        url: "/listDistrict/서울",
        success: function (data) {
            $.each(data, function () {
                let option = $("<option></option>");
                option.html(this.adName);
                option.attr("value", this.adName);
                $("#mbLoc2").append(option);
            });
        }
    });

    //인원선택 버튼 클릭 시 동호회 회원 표시
    $('#selectMember').click(function () {
        $("#memberList").empty();
        let cNo = 1;
        $.ajax({
            url: "/listClubMemberlist/" + cNo,
            success: function (data) {
                $.each(data, function () {
                    let div = $("<div></div>").addClass("col-sm-4 div-memberList");
                    let checkBox = $("<input>").attr("type", "checkbox").attr("value", this.id).attr("id", this.id).addClass("memberCheck");
                    let label = $("<label></label>").addClass("form-check-label").attr("for", this.id).html("&nbsp;&nbsp;" + this.id);
                    $(div).append(checkBox);
                    $(div).append(label);
                    $("#memberList").append(div);
                });
            }
        });
    });

    // 인원 선택 후 저장 시 이벤트
    $("#saveMatchMember").click(function(){

    });

    // 등록된 정보 입력
    $.ajax({
        url:"/detailMatch/"+mbNo,
        success: function(data){
            let m = data[0]
            console.log(m);
            //"2022-07-18T18:00:00"
            let hh = m.mbDate.substring(11,13);
            let mm = m.mbDate.substring(14,16);
            console.log(hh+" "+mm);
            $("#mbDate").val(m.mbDate.substring(0,10));
            $("#"+hh+"h").attr("selected", "true");
            $("#"+mm+"m").attr("selected", "true");
            $("#"+m.mbType).attr("selected", "true");
            $("#"+m.mbLoc1).attr("selected", "true");
            $("#"+m.mbLoc2).attr("selected", "true");
            $("#mbStadium").val(m.mbStadium);
            $("#mbFee").val(m.mbFee);
            $("#homeUcolor").val(m.homeUcolor);
            $("#homeLevel").val(m.homeLevel);
            $("#homeSay").val(m.homeSay)

            homeClub = m.cno;
            mbDate = m.mbDate.substring(0,10);
            mbTime_hh = m.mbDate.substring(11,13);
            mbTime_mm = m.mbDate.substring(14,16);
            mbType = m.mbType;
            mbLoc1 = m.mbLoc1;
            mbLoc2 = m.mbLoc2;
            mbStadium = m.mbStadium;
            mbFee = m.mbFee;
            homeUcolor = m.homeUcolor;
            homeLevel = m.homeLevel;
            homeLevel = m.homeLevel;
            homeSay = m.homeSay;

        },
        async: false
    });

    // 매치 등록 클릭 시 이벤트
    $('#offerMatch').click(function () {
        awayUcolor = $('#awayUcolor').val();
        awayLevel = $('#awayLevel').val();
        awaySay = $('#awaySay').val();


        if(awayUcolor=="" || awayLevel=="" || awaySay==""){
            alert("입력하지 않은 사항이 있습니다");
        }else{
            let data = {
                "mbNo": mbNo,
                "homeClub": homeClub,
                "mbDate": mbDate+"T"+mbTime_hh+":"+mbTime_mm+":"+"00.000Z",
                "mbType": mbType,
                "mbLoc1": mbLoc1,
                "mbLoc2": mbLoc2,
                "mbStadium": mbStadium,
                "mbFee": mbFee,
                "homeUcolor": homeUcolor,
                "homeLevel": homeLevel,
                "homeSay": homeSay,
                "mbStat": "대기",
                "mbNo": mbNo,
                "awayClub": awayClub,
                "awayUcolor": awayUcolor,
                "awayLevel": awayLevel,
                "awaySay": awaySay,
            }

            $.ajax({
                url: "/saveMatchBoard",
                type: "POST",
                data: JSON.stringify(data),
                contentType: "application/json",
                beforeSend: function (jqXHR, settings) {
                    let header = $("meta[name='_csrf_header']").attr("content");
                    let token = $("meta[name='_csrf']").attr("content");
                    jqXHR.setRequestHeader(header, token);
                },
                success: function(){
                    location.href = "/matchDetail/"+mbNo;
                }
            });
        }
    });
})