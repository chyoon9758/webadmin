$(document).ready(function() {
    $("#Go_Resize").addClass("active");
});
function Config_Resize_Add_On(){
    $('#Configure_Resize').hide();
    $('#Configure_Resize_Add').show();
}
function Config_Resize_Add_Off(){
    $('#Configure_Resize_Add').hide();
    $('#Configure_Resize').show();
}

function addRow()
{
    let rowItem = "<div class='index pb-3'>";
    rowItem += "    <div class='row'>";
    rowItem += "        <div class='w-50'>";
    rowItem += "                <div class='border'>";
    rowItem += "                    <div class='container_fluid'>";
    rowItem += "                        <div class='row'>";
    rowItem += "                            <div class='col text-right pt-2 mr-2'>";
    rowItem += "                                <button type='button' class='close' onclick='delRow(this)'><span aria-hidden='true'>×</span>";
    rowItem += "                                </button>";
    rowItem += "                            </div>";
    rowItem += "                        </div>";
    rowItem += "                    </div>";
    rowItem += "                    <div class='form-group pt-3'>";
    rowItem += "                        <div class='form-inline' role='form'>";
    rowItem += "                            <div class='container'>";
    rowItem += "                                <div class='row pb-3'>";
    rowItem += "                                     <div class='col-sm-3 align-self-center'>";
    rowItem += "                                         <span>ID</span>";
    rowItem += "                                       </div>";
    rowItem += "                                     <div class='col-sm-3 align-self-center'>";
    rowItem += "                                         <input type='text' class='form-control'' name='id' readOnly>";
    rowItem += "                                    </div>";
    rowItem += "                                    <div class='col-sm-3 align-self-center'>";
    rowItem += "                                     </div>";
    rowItem += "                                    <div class='col-sm-3 align-self-center'>";
    rowItem += "                                    </div>";
    rowItem += "                                </div>";
    rowItem += "                                <div class='row'>";
    rowItem += "                                    <div class='col-sm align-self-center'>";
    rowItem += "                                        <span>width</span>";
    rowItem += "                                    </div>";
    rowItem += "                                    <div class='col-sm align-self-center'>";
    rowItem += "                                        <input type='text' id='Config_Resize_Width' class='form-control' onkeyup='make_id(this);' oninput=this.value=this.value.replace(/[^0-9.]/g,'').replace(/(\\..*)\\./g,'$1'); name='width' required>";
    rowItem += "                                    </div>";
    rowItem += "                                    <div class='col-sm align-self-center'>";
    rowItem += "                                        <span>height</span>";
    rowItem += "                                    </div>";
    rowItem += "                                    <div class='col-sm align-self-center'>";
    rowItem += "                                        <input type='text' id='Config_Resize_Height' class='form-control' onkeyup='make_id(this);' oninput=this.value=this.value.replace(/[^0-9.]/g,'').replace(/(\\..*)\\./g,'$1'); name='height' required>";
    rowItem += "                                    </div>";
    rowItem += "                                </div>";
    rowItem += "                            </div>";
    rowItem += "                        </div";
    rowItem += "                    </div>";
    rowItem += "                </div>";
    rowItem += "            </div>";
    rowItem += "        </div>";
    rowItem += "</div>";

    $('.Resize_Card').append(rowItem); // 동적으로 row를 추가한다.

}

function delRow(object){

    let index= $(object).closest('.index');
    let indexCnt = $('.Resize_Card').find('.index');

    if(indexCnt.length != 1){
        index.remove();
    }
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
        url: "/dashboard/config/resize/chk.do",
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
                }else
                    $('Form[name=Insert_Resize]').submit();
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
            return false;
        }else if($('input[name=width]').eq(i).val()==""){
            $('input[name=width]').eq(i).focus();
            alert("width를 입력해주세요.");
            return false;
        }else if($('input[name=height]').eq(i).val()==""){
            $('input[name=height]').eq(i).focus();
            alert("height를 입력해주세요.");
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

function make_id(obj){

    let width = $(obj).closest('.index').find('input[name=width]').val();
    let height = $(obj).closest('.index').find('input[name=height]').val();
    $(obj).closest('.index').find('input[name=id]').val(width +"x"+height);
}
