$(document).ready(function() {
    $("#Go_Device").addClass("active");
});

function Config_Device_Add_On(){
    $('#Configure_Device').hide();
    $('#Configure_Device_Add').show();
}

function chk_Insert_Device(num){

    $.ajax({
        url: "/system/hellonmvt",
        data: {
            address:$('input[name=ip]').val()
        },
        async: false,
        type:"PUT",
        success: function(data) {
            if(data!=200){
                alert("IP Address를 확인해 주세요.");
                $('input[name=ip]').focus();
                return false;
            }
            if(num==1){
                $('Form[name=Update_Device]').submit();
            }else{
                $('Form[name=Insert_Device]').attr("action", "/dashboard/config/device/insert.do");
                $('Form[name=Insert_Device]').submit();
            }
        },
        error: function(data){
            console.log(data);
            alert("IP Address를 확인해 주세요.");
            $('input[name=ip]').focus();
            return false;
        }
    })
    return false;
}