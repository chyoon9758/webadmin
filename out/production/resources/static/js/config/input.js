$(document).ready(function() {
    $("#Go_Input").addClass("active");
});
function Config_Input_Add_On(){
    $('#Configure_Input').hide();
    $('#Configure_Input_Add').show();
}
function Config_Input_Add_Off(){
    $('#Configure_Input_Add').hide();
    $('#Configure_Input').show();
}
function addRow()
{
    let rowItem = "<div class='index pb-3'>";
    rowItem += "<div class='row'>";
    rowItem += "    <div class='w-50'>";
    rowItem += "        <div class='border'>";
    rowItem += "                <div class='container_fluid'>";
    rowItem += "                    <div class='row'>";
    rowItem += "                        <div class='col text-right pt-2 mr-2'>";
    rowItem += "                            <button type='button' class='close' onclick='delRow(this)'><span aria-hidden='true'>×</span>";
    rowItem += "                            </button>";
    rowItem += "                        </div>";
    rowItem += "                    </div>";
    rowItem += "                    <div class='form-group pt-3'>";
    rowItem += "                        <div class='form-inline' role='form'>";
    rowItem += "                            <div class='container'>";
    rowItem += "                                <div class='row'>";
    rowItem += "                                    <div class='col-2 align-self-center'>";
    rowItem += "                                        <span>ID</span>";
    rowItem += "                                    </div>";
    rowItem += "                                    <div class='col-4 align-self-center'>";
    rowItem += "                                        <input type='text' id='Config_Input_Id' style='width:100%' class='form-control splitvalue' name='id' required>";
    rowItem += "                                    </div>";
    rowItem += "                                    <div class='col-2 align-self-center'>";
    rowItem += "                                        <span>Interface</span>";
    rowItem += "                                    </div>";
    rowItem += "                                    <div class='col-4 align-self-center'>";
    rowItem += "                                        <input type='text' id='Config_Input_Interface' style='width:100%' class='form-control splitvalue' name='input_interface' required>";
    rowItem += "                                    </div>";
    rowItem += "                                </div>";
    rowItem += "                            </div>";
    rowItem += "                        </div>";
    rowItem += "                    </div>";
    rowItem += "                    <div class='form-group pt-3'>";
    rowItem += "                        <div class='form-inline' role='form'>";
    rowItem += "                            <div class='container'>";
    rowItem += "                                <div class='row'>";
    rowItem += "                                    <div class='col-2 align-self-center'>";
    rowItem += "                                        <span>Address</span>";
    rowItem += "                                    </div>";
    rowItem += "                                    <div class='col-4 align-self-center'>";
    rowItem += "                                        <input type='text' id='Config_Input_Address' style='width:100%' class='form-control splitvalue' name='address' required>";
    rowItem += "                                    </div>";
    rowItem += "                                    <div class='col-2 align-self-center'>";
    rowItem += "                                        <span>Broadcasted</span>";
    rowItem += "                                    </div>";
    rowItem += "                                    <div class='col-4 align-self-center'>";
    rowItem += "                                        <select class='form-select form-select-sm form-control' style='width:100%' name='broadcasted' required>";
    rowItem += "                                            <option value='no' selected>No</option>";
    rowItem += "                                            <option value='yes'>Yes</option>";
    rowItem += "                                        </select>";
    rowItem += "                                    </div>";
    rowItem += "                                </div>";
    rowItem += "                            </div>";
    rowItem += "                        </div";
    rowItem += "                    </div>";
    rowItem += "                </div>";
    rowItem += "            </div>";
    rowItem += "        </div>";
    rowItem += "</div>";

    let indexCnt = $('.Input_Card').find('.index');
    if(indexCnt.length < 12)
        $('.Input_Card').append(rowItem); // 동적으로 row를 추가한다.

}

function delRow(object){

    let index= $(object).closest('.index');
    let indexCnt = $('.Input_Card').find('.index');

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

function chk_GroupId(num){

    let group_id = $('input[name=group_id]').val();

    $.ajax({
        url: "/dashboard/config/input/chk.do",
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
                    $('Form[name=Insert_Input]').submit();

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
        }else if($('input[name=input_interface]').eq(i).val()==""){
            $('input[name=input_interface]').eq(i).focus();
            alert("interface를 입력해주세요.");
            exit=true;
            return false;
        }else if($('input[name=address]').eq(i).val()==""){
            $('input[name=address]').eq(i).focus();
            alert("address를 입력해주세요.");
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