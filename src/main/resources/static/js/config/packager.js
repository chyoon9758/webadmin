$(document).ready(function() {
    $("#Go_Packager").addClass("active");
});

function Config_Packager_Add_On(){
    $('#Configure_Packager').hide();
    $('#Configure_Packager_Add').show();
}

function chkDelete(){

    let useCurrent = $('input[name=selected]').val();

    if(useCurrent==1){
        alert('Role에서 사용 중 입니다.');
        return false;
    }else if(!confirm('삭제하시면 복구할 수 없습니다. \n정말로 삭제하시겠습니까?')){
        return false;
    }
}

let exit = false;

function chk_GroupId(){

    let group_id = $('input[name=group_id]').val();

    $.ajax({
        url: "/dashboard/config/packager/chk.do",
        data: {
            group_id:group_id
        },
        type:"POST",
        success: function(data) {
            if(data.length!=0){
                alert('group_id가 중복입니다.');
            }else{

                chk_Value();

                if(exit){
                    return false;
                }else
                    $('Form[name=Insert_Packager]').submit();
            }
        }
    })
    return false;
}

function chk_Value(){

    if($('input[name=group_id]').val()=="") {
        $('input[name=group_id]').focus();
        alert("group_id를 입력해주세요.");
        exit=true;
        return false;
    }else if($('input[name=kms_url]').val()==""){
        $('input[name=kms_url]').focus();
        alert("kms_url를 입력해주세요.");
        exit=true;
        return false;
    }else if($('input[name=kms_token]').val()==""){
        $('input[name=kms_token]').focus();
        alert("kms_token를 입력해주세요.");
        exit=true;
        return false;
    }else if($('input[name=content_id]').val()==""){
        $('input[name=content_id]').focus();
        alert("content_id를 입력해주세요.");
        exit=true;
        return false;
    }else if($('input[name=service]').val()==""){
        $('input[name=service]').focus();
        alert("service를 입력해주세요.");
        exit=true;
        return false;
    }else if($('input[name=container]').val()==""){
        $('input[name=container]').focus();
        alert("container를 입력해주세요.");
        exit=true;
        return false;
    }else if($('input[name=mpd_path]').val()==""){
        $('input[name=mpd_path]').focus();
        alert("mpd_path를 입력해주세요.");
        exit=true;
        return false;
    }else if($('input[name=mpd_name]').val()==""){
        $('input[name=mpd_name]').focus();
        alert("mpd_name를 입력해주세요.");
        exit=true;
        return false;
    }else if($('input[name=segment_path_prefix]').val()==""){
        $('input[name=segment_path_prefix]').focus();
        alert("segment_path_prefix를 입력해주세요.");
        exit=true;
        return false;
    }else if($('input[name=m3u8_path]').val()==""){
        $('input[name=m3u8_path]').focus();
        alert("m3u8_path를 입력해주세요.");
        exit=true;
        return false;
    }else if($('input[name=m3u8_name]').val()==""){
        $('input[name=m3u8_name]').focus();
        alert("m3u8_name를 입력해주세요.");
        exit=true;
        return false;
    }else if($('input[name=segment_duration]').val()==""){
        $('input[name=segment_duration]').focus();
        alert("segment_duration를 입력해주세요.");
        exit=true;
        return false;
    }else if($('input[name=window]').val()==""){
        $('input[name=window]').focus();
        alert("window를 입력해주세요.");
        exit=true;
        return false;
    }else if($('input[name=minupdate_period]').val()==""){
        $('input[name=minupdate_period]').focus();
        alert("minupdate_period를 입력해주세요.");
        exit=true;
        return false;
    }else if($('input[name=preserved_segments]').val()==""){
        $('input[name=preserved_segments]').focus();
        alert("preserved_segments를 입력해주세요.");
        exit=true;
        return false;
    }else if($('input[name=name]').val()==""){
        $('input[name=name]').focus();
        alert("name를 입력해주세요.");
        exit=true;
        return false;
    }else if($('input[name=accessKey]').val()==""){
        $('input[name=accessKey]').focus();
        alert("accessKey를 입력해주세요.");
        exit=true;
        return false;
    }else if($('input[name=endpoint]').val()==""){
        $('input[name=endpoint]').focus();
        alert("endpoint를 입력해주세요.");
        exit=true;
        return false;
    }else if($('input[name=secretKey]').val()==""){
        $('input[name=secretKey]').focus();
        alert("secretKey를 입력해주세요.");
        exit=true;
        return false;
    }else if($('input[name=region]').val()==""){
        $('input[name=region]').focus();
        alert("region를 입력해주세요.");
        exit=true;
        return false;
    }
    exit=false;
}