$(function(){
    let CDate = new Date();
    let today = new Date();
    let selectCk = 0;

    let printCalendar = function(year, month){
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
            if(i < thisFirst.getDay()){
                htmlDates += '<div class="date last">'+dates[i]+'</div>';
            }else if(today.getDate()==dates[i] && today.getMonth()==CDate.getMonth() && today.getFullYear()==CDate.getFullYear()){
                htmlDates += '<div id="date_'+dates[i]+'" class="date today">'+dates[i]+'</div>';
            }else if(i >= thisFirst.getDay() + thisLast.getDate()){
                htmlDates += '<div class="date next">'+dates[i]+'</div>';
            }else{
                htmlDates += '<div id="date_'+dates[i]+'" class="date">'+dates[i]+'</div>';
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
    }

    // function fn_selectDate(date){
    //     let year = CDate.getFullYear();
    //     let month = CDate.getMonth() + 1;
    //     let date_txt = "";
    //     if(CDate.getMonth + 1 < 10){
    //         month = "0" + (CDate.getMonth() + 1);
    //     }
    //     if(date < 10){
    //         date_txt = "0" + date;
    //     }
    //
    //     if(selectCk == 0){
    //         $(".date").css("background-color", "");
    //         $(".date").css("color", "");
    //         $("#date_"+date).css("background-color", "red");
    //         $("#date_"+date).css("color", "white");
    //
    //         $("#period_1").val(year+"-"+month+"-"+date);
    //         $("#period_2").val("");
    //         selectCk = date;
    //     }else{
    //         $(".date").css("background-color", "");
    //         $(".date").css("color", "");
    //         $("#date_"+date).css("background-color", "red");
    //         $("#date_"+date).css("color", "white");
    //
    //         $("#period_2").val(year+"-"+month+"-"+date);
    //         selectCk = 0;
    //     }
    // }

    printCalendar();

    $(document).on("click", "#prevCal", function(){
        $('#calendarContainer').empty();
        CDate.setMonth(CDate.getMonth()-1);
        printCalendar();
    });

    $(document).on("click", "#nextCal", function(){
        $('#calendarContainer').empty();
        CDate.setMonth(CDate.getMonth()+1);
        printCalendar();
    });

});

