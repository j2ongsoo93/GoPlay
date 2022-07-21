$(function() {
    //TODO: 입력값 validation
    let mLoc1 = null;
    let mLoc2 = null;


    //시도 지역선택 드롭다운 리스트
    $.ajax({
        url: "/listCity",
        success: function (data) {
            $.each(data, function () {
                let option = $("<option></option>");
                option.html(this.acName);
                option.attr("value", this.acName);
                option.attr("acNo", this.acNo);
                $("#mLoc1").append(option);
            });
        }
    });

    // 지역 선택 시 이벤트
    $(document).on("change", "#mLoc1", function () {
        $("#matchContainer").empty();
        $("#mLoc2").empty();
        let mLoc1 = $(this).val();
        //시도 지역분류 선택 시 세부지역 출력
        $.ajax({
            url: "/listDistrict/" + mLoc1,
            success: function (data) {
                $.each(data, function () {
                    let option = $("<option></option>");
                    option.html(this.adName);
                    option.attr("value", this.adName);
                    $("#mLoc2").append(option);
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
                $("#mLoc2").append(option);
            });
        }
    });

})