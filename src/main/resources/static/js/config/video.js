$(document).ready(function() {
    $("#Go_Video").addClass("active"); // 시작시 버튼 활성화 (해당 탭에 있다는걸 표시)
});
function Config_Video_Add_On(){ //추가하기 탭 활성화
    $('#Configure_Video').hide();
    $('#Configure_Video_Add').show();
}
function addRow(obj)
{
    let rowItem = "<div class='index pb-3'>";
    rowItem += "<div class='row'>";
    rowItem += "<div class='w-75 pt-3'>";
    rowItem += "<div class='border'>";
    rowItem += "<div class='container_fluid'>";
    rowItem += "<div class='row'>";
    rowItem += "<div class='col text-right pt-2 mr-2'>";
    rowItem += "<button type='button' class='close' onclick='delRow(this)'>";
    rowItem += "<span aria-hidden='true'>×</span>";
    rowItem += "</button>";
    rowItem += "</div>";
    rowItem += "</div>";
    rowItem += "</div>";
    rowItem += "<div class='form-group pt-3'>";
    rowItem += "<div class='form-inline'>";
    rowItem += "<div class='container'>";
    rowItem += "<div class='row'>";
    rowItem += "<div class='col-1 align-self-center'>";
    rowItem += "<span>ID</span>";
    rowItem += "</div>";
    rowItem += "<div class='col-3 align-self-center'>";
    rowItem += "<input type='text' class='form-control' required name='id'>";
    rowItem += "</div>";
    rowItem += "<div class='col-1 align-self-center'>";
    rowItem += "<span>GOP</span>";
    rowItem += "</div>";
    rowItem += "<div class='col-3 align-self-center'>";
    rowItem += "<select class='form-select form-select-sm form-control' style='width:202px;' required name='iframe_interval'>";
    rowItem += "<option value='15'>15</option>";
    rowItem += "<option value='30' selected>30</option>";
    rowItem += "<option value='60'>60</option>";
    rowItem += "</select>";
    rowItem += "</div>";
    rowItem += "<div class='col-1 align-self-center'>";
    rowItem += "<span>Bitrate</span>";
    rowItem += "</div>";
    rowItem += "<div class='col-3 align-self-center'>";
    rowItem += "<input type='text' class='form-control'  onkeypress='inNumber();' required name='bitrate'/>";
    rowItem += "</div>";
    rowItem += "</div>";
    rowItem += "</div>";
    rowItem += "</div>";
    rowItem += "<div class='form-group pt-3'>";
    rowItem += "<div class='form-inline'>";
    rowItem += "<div class='container'>";
    rowItem += "<div class='row'>";
    rowItem += "<div class='col-1 align-self-center'>";
    rowItem += "<span>Codec</span>";
    rowItem += "</div>";
    rowItem += "<div class='col-3 align-self-center'>";
    rowItem += "<input type='text' class='form-control' name='codec' value='h264' readonly>";
    rowItem += "</div>";
    rowItem += "<div class='col-1 align-self-center'>";
    rowItem += "<span>FPS</span>";
    rowItem += "</div>";
    rowItem += "<div class='col-3 align-self-center'>";
    rowItem += "<input type='text' class='form-control' name='fps' value='29.97' readonly>";
    rowItem += "</div>";
    rowItem += "<div class='col-1 align-self-center'>";
    rowItem += "<span>Profile</span>";
    rowItem += "</div>";
    rowItem += "<div class='col-3 align-self-center'>";
    rowItem += "<select class='form-select form-select-sm form-control' style='width:202px;' required name='profile'>";
    rowItem += "<option value='Base'>Base</option>";
    rowItem += "<option value='Main'>Main</option>";
    rowItem += "<option value='High'>High</option>";
    rowItem += "<option value='Auto' selected>Auto</option>";
    rowItem += "</select>";
    rowItem += "</div>";
    rowItem += "</div>";
    rowItem += "</div>";
    rowItem += "</div>";
    rowItem += "</div>";
    rowItem += "<div class='form-group pt-3'>";
    rowItem += "<div class='form-inline'>";
    rowItem += "<div class='container'>";
    rowItem += "<div class='row'>";
    rowItem += "<div class='col-1 align-self-center'>";
    rowItem += "<span>Width X height</span>";
    rowItem += "</div>";
    rowItem += "<div class='col-3 align-self-center'>";
    rowItem += "<input type='text' class='form-control w-25'  onkeypress='inNumber();' required name='width'>X";
    rowItem += "<input type='text' class='form-control w-25'  onkeypress='inNumber();' required name='height'>";
    rowItem += "</div>";
    rowItem += "<div class='col-1 align-self-center'>";
    rowItem += "<span>Level</span>";
    rowItem += "</div>";
    rowItem += "<div class='col-3 align-self-center'>";
    rowItem += "<input type='text' class='form-control' required name='level'>";
    rowItem += "</div>";
    rowItem += "<div class='col-1 align-self-center'>";
    rowItem += "</div>";
    rowItem += "<div class='col-3 align-self-center'>";
    rowItem += "</div>";
    rowItem += "</div>";
    rowItem += "</div>";
    rowItem += "</div>";
    rowItem += "</div>";
    rowItem += "</div>";
    rowItem += "</div>";
    rowItem += "</div>";
    rowItem += "</div>";
    rowItem += "</div>";

    $(obj).closest('.card-body').find('.Video_Card').append(rowItem); // 동적으로 row를 추가한다.

}

function delRow(object){

    let index= $(object).closest('.index');
    let indexCnt = $(object).closest('.Video_Card').find('.index');

    if(indexCnt.length != 1){
        index.remove();  // 동적으로 row를 삭제한다.
    }
}

//숫자만 입력받도록 한다.
function inNumber(){
    if(event.keyCode<48 || event.keyCode>57){
        event.returnValue=false;
    }
}

let exit = false;

function chkDelete(){

    let useCurrent = $('input[name=selected]').val();

    if(useCurrent==1){
        alert('Role에서 사용 중 입니다.');
        return false;
    }else if(!confirm('삭제하시면 복구할 수 없습니다. \n정말로 삭제하시겠습니까?')){
        return false;
    }
}

function chk_GroupId(num){

    let group_id = $('input[name=group_id]').val();

    $.ajax({
        url: "/dashboard/config/video/chk.do",
        data: {
            group_id:group_id
        },
        type:"POST",
        success: function(data) {
            if(data.length>0){
                alert('group_id가 중복입니다.');
            }else{
                if($('input[name=group_id]').val()=="") {
                    $('input[name=group_id]').focus();
                    alert("group_id를 입력해주세요.");
                    return false;
                }

                chk_Value();

                if(exit){
                    return false;
                }else if(num==1){
                    $('Form[name=Update_Video]').attr("action", "/dashboard/config/video/insert.do");
                    $('Form[name=Update_Video]').submit();
                }else
                    $('Form[name=Insert_Video]').submit();
            }
        }
    })
    return false;
}

function chk_Value(){
    for(let i=0; i<$('.index').length; i++){
        if($('input[name=id]').eq(i).val()=="") {
            $('input[name=id]').eq(i).focus();
            alert("id를 입력해주세요.");
            exit=true;
            return false;
        }else if($('input[name=bitrate]').eq(i).val()==""){
            $('input[name=bitrate]').eq(i).focus();
            alert("bitrate를 입력해주세요.");
            exit=true;
            return false;
        }else if($('input[name=width]').eq(i).val()==""){
            $('input[name=width]').eq(i).focus();
            alert("width를 입력해주세요.");
            exit=true;
            return false;
        }else if($('input[name=height]').eq(i).val()==""){
            $('input[name=height]').eq(i).focus();
            alert("height를 입력해주세요.");
            exit=true;
            return false;
        }else if($('input[name=level]').eq(i).val()==""){
            $('input[name=level]').eq(i).focus();
            alert("level를 입력해주세요.");
            exit=true;
            return false;
        }
        for(let j=0; j<i; j++){
            if ($('input[name=id]').eq(i).val() == $('input[name=id]').eq(j).val()) {
                $('input[name=id]').eq(i).focus();
                alert("id가 중복입니다.");
                exit=true;
                return false;
            }
        }
    }
    exit=false;
}