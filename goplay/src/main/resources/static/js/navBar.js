$(function () {
    let currentid = 'null'; //로그인 한 id
    $.ajax({
        url: "/loginMemberID",
        async: false,
        success: function (data) {
            currentid = data
            console.log("----")
            console.log(currentid)
        }
    });
    $('#loginid').val(currentid);

    $('#loginid').text(currentid);


    $.ajax({
        url: "/loginMemberNickname",
        async: false,
        success: function (data) {
            currentnickname = data
            console.log("----")
            console.log(currentnickname)
        }
    });

    $('#loginnickname').val(currentnickname);

    $('#loginnickname').text(currentnickname);

})