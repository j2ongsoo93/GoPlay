$(function(){
    let CDate = new Date();
    let today = new Date();
    let selectCk = 0;
    let thisFirst;
    let thisLast;

    let printCalendar = function(thisData){
        $('#calendarContainer').empty(); //각 개별 날짜 버튼 눌렀을 때 계속 캘린더 다시 그려줘서 empty로 지워버림
        let htmlDates = '';
        let prevLast = new Date(CDate.getFullYear(), CDate.getMonth(), 0); //지난 달의 마지막 날
        thisFirst = new Date(CDate.getFullYear(), CDate.getMonth(), 1); //이번 달의 첫쨰 날
        thisLast = new Date(CDate.getFullYear(), CDate.getMonth() + 1, 0); //이번 달의 마지막 날
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
                htmlDates += '<div id='+CDate.getFullYear()+'-'+convertMonth(CDate.getMonth()+1)+'-'+convertMonth(dates[i])+' class="date today matchDate"><div>'+dates[i]+'</div></div>';
            }else if(i >= thisFirst.getDay() + thisLast.getDate()){
                htmlDates += '<div class="date next">'+dates[i]+'</div>';
            }else{
                htmlDates += '<div id='+CDate.getFullYear()+'-'+convertMonth(CDate.getMonth()+1)+'-'+convertMonth(dates[i])+' class="date matchDate"><div>'+dates[i]+'</div></div>';
            }
        }

        let calendarForm =
            $("<div class='calendar' style='margin-left:60px'></div>")
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

    const offset = new Date().getTimezoneOffset() * 60000;
    function printSCH(thisData){
        if ($(thisData).attr("class")=='date matchDate' || $(thisData).attr("class")=='date today matchDate'){

            thisFirstISO = (new Date($(thisData).attr("id")).toISOString()); //현재날짜 00 시 부터
            thisLastISO = (new Date(($(thisData).next()).attr("id")).toISOString()); // 다음날 00시까지 조회 (즉 1일 조회)
        }
        else{
            thisFirstISO = (new Date(thisFirst - offset)).toISOString();
            thisLastISO = (new Date(thisLast - offset+86399999)).toISOString();
        }

        $("#scheduleTable").empty();
        $.ajax({
            url:"/listBoardSch",
            data:{
                "thisFirstISO":thisFirstISO,
                "thisLastISO":thisLastISO
            },
            type: "POST",
            beforeSend: function (jqXHR, settings) {
                let header = $("meta[name='_csrf_header']").attr("content");
                let token = $("meta[name='_csrf']").attr("content");
                jqXHR.setRequestHeader(header, token);
            },
            success:function(data){
                $.each(data['content'],function(){
                    var newSchDate = this.schDate.substring(0,19).replace("T", " ");
                    console.log(this)
                    $("#scheduleTable")
                        .append($("<tr></tr>")
                            .append($("<td></td>").attr("scope","row").html("행사"))
                            .append($("<td></td>").html(newSchDate))
                            .append($("<td></td>").html(this.bcontent))
                            .append($("<td></td>").html(this.schPlace))
                            .append($("<td></td>").html("-"))
                        )
                })
            }
        })
        $.ajax({
            url:"/listMatchCno",
            data:{
                "thisFirstISO":thisFirstISO,
                "thisLastISO":thisLastISO
            },
            type: "POST",
            beforeSend: function (jqXHR, settings) {
                let header = $("meta[name='_csrf_header']").attr("content");
                let token = $("meta[name='_csrf']").attr("content");
                jqXHR.setRequestHeader(header, token);
            },
            success:function(data2){
                $.each(data2['content'],function(){
                    var newMbDate = this.mbDate.substring(0,19).replace("T", " ");
                    $("#scheduleTable")
                        .append($("<tr></tr>")
                            .append($("<td></td>").attr("scope","row").html("경기"))
                            .append($("<td></td>").html(newMbDate))
                            .append($("<td></td>").html(this.awaySay))
                            .append($("<td></td>").html(this.mbStadium))
                            .append($("<td></td>").html(this.mbStat))
                        )
                })
            }
        })
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

    $(document).on("click", "#prevCal", function(){
        $('#calendarContainer').empty();
        CDate.setMonth(CDate.getMonth()-1);
        printCalendar();
        printSCH(this)
    });

    $(document).on("click", "#nextCal", function(){
        $('#calendarContainer').empty();
        CDate.setMonth(CDate.getMonth()+1);
        printCalendar();
        printSCH(this)

    });

    $(document).on("click", ".date", function(){
        printCalendar();
        printSCH(this)
    });

});
