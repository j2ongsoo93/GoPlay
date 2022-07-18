$(function(){
    let CDate = new Date();
    let today = new Date();
    let selectCk = 0;

    let printCalendar = function(){
        let htmlDates = '';
        let prevLast = new Date(CDate.getFullYear(), CDate.getMonth(), 0); //지난 달의 마지막 날
        let thisFirst = new Date(CDate.getFullYear(), CDate.getMonth(), 1); //이번 달의 첫쨰 날
        let thisLast = new Date(CDate.getFullYear(), CDate.getMonth() + 1, 0); //이번 달의 마지막 날
        const dates = [];

        if(thisFirst.getDay()!=0){
            for(let i = 0; i < thisFirst.getDay(); i++){
                dates.unshift(prevLast.getDate()-i); // 지난 달 날짜 채우기
            }
        }
        for(let i = 1; i <= thisLast.getDate(); i++){
            dates.push(i); // 이번 달 날짜 채우기
        }
        for(let i = 1; i <= 13 - thisLast.getDay(); i++){
            dates.push(i); // 다음 달 날짜 채우기 (나머지 다 채운 다음 출력할 때 42개만 출력함)
        }


        for(let i = 0; i < 42; i++){
            let year = CDate.getFullYear();
            let month = convertMonth(CDate.getMonth()+1);
            let day = convertMonth(dates[i]);
            if(i < thisFirst.getDay()){
                htmlDates += '<div class="date last">'+dates[i]+'</div>';
            }else if(today.getDate()==dates[i] && today.getMonth()==CDate.getMonth() && today.getFullYear()==CDate.getFullYear()){
                htmlDates += '<div id='+CDate.getFullYear()+'-'+convertMonth(CDate.getMonth()+1)+'-'+convertMonth(dates[i])+' class="date today matchDate"><div>'+dates[i]+'</div></div>';
            }else if(i >= thisFirst.getDay() + thisLast.getDate()){
                htmlDates += '<div class="date next">'+dates[i]+'</div>';
            }else{
                htmlDates += '<div id='+CDate.getFullYear()+'-'+convertMonth(CDate.getMonth()+1)+'-'+convertMonth(dates[i])+' class="date matchDate"><div>'+dates[i]+'</div></div>';
            }
        }

        let calendarForm =
            $("<div class='calendar'></div>")
                .append($("<div class='header'></div>")
                    .append($('<button class="calendar_btn" id="prevCal">&lt</button>'))
                    .append($('<div class="title"><span class="year">'+CDate.getFullYear()+'년 </span><span class="month"> '+(CDate.getMonth() + 1)+'월</span></div>'))
                    .append($('<button class="calendar_btn" id="nextCal">&gt</button>')))
                .append($('<div class="day"></div>')
                    .append($('<div>일</div><div>월</div><div>화</div><div>수</div><div>목</div><div>금</div><div>토</div>')))
                .append($('<div class="dates"></div>')
                    .append($(htmlDates)));

        $('#calendarContainer').append(calendarForm);

        $.ajax({
            url: "/matchDate",
            success: function(data){
                $.each(data, function(){
                    //"2022-06-11T00:00:00"
                    let matchDate = this.mbDate.substring(0, 10);
                    let match_cnt = this.cnt;
                    console.log(matchDate +"->"+match_cnt);
                    let link_date = $('<p style="margin-top: -20px; color: red">'+match_cnt+'경기</p>').attr("id", match_cnt);
                    $("#"+matchDate).append(link_date);
                });
            }
        });
    }

    // 한자리 숫자 앞에 0붙여서 String 반환하는 function
    let convertMonth = function(n){
        let str = n;
        if(n<10){
            str = "0"+n;
            return str;
        }else{
            return n;
        }

    }

    printCalendar();

    // 이전 달 달력 출력
    $(document).on("click", "#prevCal", function(){
        $('#calendarContainer').empty();
        CDate.setMonth(CDate.getMonth()-1);
        printCalendar();
    });

    // 다음 달 달력 출력
    $(document).on("click", "#nextCal", function(){
        $('#calendarContainer').empty();
        CDate.setMonth(CDate.getMonth()+1);
        printCalendar();
    });

});

