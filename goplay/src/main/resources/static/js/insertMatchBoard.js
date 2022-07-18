$(function() {
    //TODO: 입력값 validation

    //시도 지역선택 드롭다운 리스트
    $.ajax({
        url: "/listCity",
        success: function (data) {
            $.each(data, function () {
                let option = $("<option></option>");
                option.html(this.acName);
                option.attr("value", this.acName);
                option.attr("acNo", this.acNo);
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

    let mbNo = null;
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
    
    // 매치 등록 클릭 시 이벤트
    $('#insertMatch').click(function () {
        mbDate = $("#mbDate").val();
        mbTime_hh = $("#mbTime_hh").val();
        mbTime_mm = $("#mbTime_mm").val();
        mbType = $('#mbType').val();
        mbLoc1 = $('#mbLoc1').val();
        mbLoc2 = $('#mbLoc2').val();
        mbStadium = $('#mbStadium').val();
        mbFee = $('#mbFee').val();
        homeUcolor = $('#homeUcolor').val();
        homeLevel = $('#homeLevel').val();
        homeSay = $('#homeSay').val();

        //시간이 1자리 수면 0 붙이기
        if (mbTime_hh < 10) {
            mbTime_hh = "0" + mbTime_hh;
        }

        if (mbTime_mm < 10) {
            mbTime_mm = "0" + mbTime_mm;
        }

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
            "mbStat": "대기"
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
            }
        });
        console.log(data);
        console.log(mbTime_hh, mbTime_mm);
        console.log(new Date(mbDate).setHours(mbTime_hh, mbTime_mm));
        console.log(new Date(1658352600000))
    });
})