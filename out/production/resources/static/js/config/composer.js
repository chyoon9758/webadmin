$(document).ready(function() {
    $("#Go_Composer").addClass("active");
});
function Config_Composer_Add_On(){
    $('#Configure_Composer').hide();
    $('#Configure_Composer_Add').show();
}
function Config_Composer_Add_Off(){
    $('#Configure_Composer_Add').hide();
    $('#Configure_Composer').show();
}

function addRow()
{
    let rowItem = "<div class='index pb-3'>";
    rowItem += "    <div class='row'>";
    rowItem += "        <div class='w-75'>";
    rowItem += "            <div class='border pb-3'>";
    rowItem += "                <div class='container_fluid'>";
    rowItem += "                    <div class='row'>";
    rowItem += "                        <div class='col text-right pt-2 mr-2'>";
    rowItem += "                            <button type='button' class='close' onClick='delRow(this)'><span aria-hidden='true'>×</span>";
    rowItem += "                            </button>";
    rowItem += "                        </div>";
    rowItem += "                    </div>";
    rowItem += "                </div>";
    rowItem += "                <div class='form-group pt-3'>";
    rowItem += "                    <div class='form-inline' role='form'>";
    rowItem += "                        <div class='container'>";
    rowItem += "                            <div class='row'>";
    rowItem += "                                <div class='col-sm align-self-center'>";
    rowItem += "                                    <span>ID</span>";
    rowItem += "                                </div>";
    rowItem += "                                <div class='col-sm align-self-center'>";
    rowItem += "                                    <input type='text' id='Config_Composer_Id' class='form-control item_id splitvalue' name='id' required>";
    rowItem += "                                </div>";
    rowItem += "                                <div class='col-sm align-self-center'>";
    rowItem += "                                    <span>Width X Height</span>";
    rowItem += "                                </div>";
    rowItem += "                                <div class='col-sm align-self-center'>";
    rowItem += "                                    <input type='text' id='Config_Composer_Width' class='form-control item_width' style='width:42%' name='width' oninput=this.value=this.value.replace(/[^0-9.]/g,'').replace(/(\\..*)\\./g,'$1'); required>X";
    rowItem += "                                    <input type='text' id='Config_Composer_Height' class='form-control item_height' style='width:42%' name='height' oninput=this.value=this.value.replace(/[^0-9.]/g,'').replace(/(\\..*)\\./g,'$1'); required>";
    rowItem += "                                </div>";
    rowItem += "                                <div class='col-sm'></div>";
    rowItem += "                                <div class='col-sm'></div>";
    rowItem += "                            </div>";
    rowItem += "                        </div>";
    rowItem += "                    </div>";
    rowItem += "                </div>";
    rowItem += "                <div class='container'>";
    rowItem += "                    <div class='row'>";
    rowItem += "                        <div class='col-11'>";
    rowItem += "                            <div class='Composer_Item'>";
    rowItem += "                                <div class='subindex pb-1'>";
    rowItem += "                                    <div class='border'>";
    rowItem += "                                        <div class='container_fluid'>";
    rowItem += "                                            <div class='row'>";
    rowItem += "                                                <div class='col text-right pt-2 mr-2'>";
    rowItem += "                                                    <button type='button' class='close' onClick='delSubRow(this)'>";
    rowItem += "                                                        <span aria-hidden='true'>×</span>";
    rowItem += "                                                    </button>";
    rowItem += "                                                </div>";
    rowItem += "                                            </div>";
    rowItem += "                                        </div>";
    rowItem += "                                        <div class='form-group pt-3'>";
    rowItem += "                                            <div class='form-inline' role='form'>";
    rowItem += "                                                <div class='container'>";
    rowItem += "                                                    <div class='row'>";
    rowItem += "                                                        <div class='col-sm align-self-center'>";
    rowItem += "                                                            <span>left</span>";
    rowItem += "                                                        </div>";
    rowItem += "                                                        <div class='col-sm align-self-center'>";
    rowItem += "                                                            <input type='text' id='Config_Composer_Left' class='form-control' style='width:70%' name='left' oninput=this.value=this.value.replace(/[^0-9.]/g,'').replace(/(\\..*)\\./g,'$1'); required>";
    rowItem += "                                                        </div>";
    rowItem += "                                                        <div class='col-sm align-self-center'>";
    rowItem += "                                                            <span>top</span>";
    rowItem += "                                                        </div>";
    rowItem += "                                                        <div class='col-sm align-self-center'>";
    rowItem += "                                                            <input type='text' id='Config_Composer_Top' class='form-control' style='width:70%' name='top' oninput=this.value=this.value.replace(/[^0-9.]/g,'').replace(/(\\..*)\\./g,'$1'); required>";
    rowItem += "                                                        </div>";
    rowItem += "                                                        <div class='col-sm align-self-center'>";
    rowItem += "                                                            <span>right</span>";
    rowItem += "                                                        </div>";
    rowItem += "                                                        <div class='col-sm align-self-center'>";
    rowItem += "                                                            <input type='text' id='Config_Composer_Right' class='form-control' style='width:70%' name='right' oninput=this.value=this.value.replace(/[^0-9.]/g,'').replace(/(\\..*)\\./g,'$1'); required>";
    rowItem += "                                                        </div>";
    rowItem += "                                                        <div class='col-sm align-self-center'>";
    rowItem += "                                                            <span>bottom</span>";
    rowItem += "                                                        </div>";
    rowItem += "                                                        <div class='col-sm align-self-center'>";
    rowItem += "                                                            <input type='text' id='Config_Composer_Bottom' class='form-control' style='width:70%' name='bottom' oninput=this.value=this.value.replace(/[^0-9.]/g,'').replace(/(\\..*)\\./g,'$1'); required>";
    rowItem += "                                                        </div>";
    rowItem += "                                                    </div>";
    rowItem += "                                                </div>";
    rowItem += "                                            </div>";
    rowItem += "                                        </div>";
    rowItem += "                                    </div>";
    rowItem += "                                </div>";
    rowItem += "                                <div class='subindex pb-1'>";
    rowItem += "                                    <input type='hidden' name='id'>";
    rowItem += "                                    <input type='hidden' name='width' value=0>";
    rowItem += "                                    <input type='hidden' name='height' value=0>";
    rowItem += "                                    <div class='border'>";
    rowItem += "                                        <div class='container_fluid'>";
    rowItem += "                                            <div class='row'>";
    rowItem += "                                                <div class='col text-right pt-2 mr-2'>";
    rowItem += "                                                    <button type='button' class='close' onClick='delSubRow(this)'>";
    rowItem += "                                                        <span aria-hidden='true'>×</span>";
    rowItem += "                                                    </button>";
    rowItem += "                                                </div>";
    rowItem += "                                            </div>";
    rowItem += "                                        </div>";
    rowItem += "                                        <div class='form-group pt-3'>";
    rowItem += "                                            <div class='form-inline' role='form'>";
    rowItem += "                                                <div class='container'>";
    rowItem += "                                                    <div class='row'>";
    rowItem += "                                                        <div class='col-sm align-self-center'>";
    rowItem += "                                                            <span>left</span>";
    rowItem += "                                                        </div>";
    rowItem += "                                                        <div class='col-sm align-self-center'>";
    rowItem += "                                                            <input type='text' id='Config_Composer_Left' class='form-control' style='width:70%' name='left' oninput=this.value=this.value.replace(/[^0-9.]/g,'').replace(/(\\..*)\\./g,'$1'); required>";
    rowItem += "                                                        </div>";
    rowItem += "                                                        <div class='col-sm align-self-center'>";
    rowItem += "                                                            <span>top</span>";
    rowItem += "                                                        </div>";
    rowItem += "                                                        <div class='col-sm align-self-center'>";
    rowItem += "                                                            <input type='text' id='Config_Composer_Top' class='form-control' style='width:70%' name='top' oninput=this.value=this.value.replace(/[^0-9.]/g,'').replace(/(\\..*)\\./g,'$1'); required>";
    rowItem += "                                                        </div>";
    rowItem += "                                                        <div class='col-sm align-self-center'>";
    rowItem += "                                                            <span>right</span>";
    rowItem += "                                                        </div>";
    rowItem += "                                                        <div class='col-sm align-self-center'>";
    rowItem += "                                                            <input type='text' id='Config_Composer_Right' class='form-control' style='width:70%' name='right' oninput=this.value=this.value.replace(/[^0-9.]/g,'').replace(/(\\..*)\\./g,'$1'); required>";
    rowItem += "                                                        </div>";
    rowItem += "                                                        <div class='col-sm align-self-center'>";
    rowItem += "                                                            <span>bottom</span>";
    rowItem += "                                                        </div>";
    rowItem += "                                                        <div class='col-sm align-self-center'>";
    rowItem += "                                                            <input type='text' id='Config_Composer_Bottom' class='form-control' style='width:70%' name='bottom' oninput=this.value=this.value.replace(/[^0-9.]/g,'').replace(/(\\..*)\\./g,'$1'); required>";
    rowItem += "                                                        </div>";
    rowItem += "                                                    </div>";
    rowItem += "                                                </div>";
    rowItem += "                                            </div>";
    rowItem += "                                        </div>";
    rowItem += "                                    </div>";
    rowItem += "                                </div>";
    rowItem += "                            </div>";
    rowItem += "                        </div>";
    rowItem += "                        <div class='align-self-end col'>";
    rowItem += "                            <button type='button' class='btn btn-circle btn-info' onclick='addSubRow(this)'><h5>+</h5></button>";
    rowItem += "                        </div>";
    rowItem += "                    </div>";
    rowItem += "                </div>";
    rowItem += "           </div>";
    rowItem += "        </div>";
    rowItem += "    </div>";

    let indexCnt = $('.Composer_Card').find('.index');
    if(indexCnt.length < 4){
        $('.Composer_Card').append(rowItem); // 동적으로 row를 추가한다.
    }

}

function delRow(obj){

    let index= $(obj).closest('.index');
    let indexCnt = $('.Composer_Card').find('.index');

    if(indexCnt.length != 1){
        index.remove();
    }
}

function addSubRow(obj){

    let rowItem = "<div class='subindex pb-1'>";
    rowItem += "    <input type='hidden' name='id'>";
    rowItem += "    <input type='hidden' name='width' value=0>";
    rowItem += "    <input type='hidden' name='height' value=0>";
    rowItem += "    <div class='border'>";
    rowItem += "        <div class='container_fluid'>";
    rowItem += "            <div class='row'>";
    rowItem += "                <div class='col text-right pt-2 mr-2'>";
    rowItem += "                    <button type='button' class='close' onClick='delSubRow(this)'>";
    rowItem += "                        <span aria-hidden='true'>×</span>";
    rowItem += "                    </button>";
    rowItem += "                </div>";
    rowItem += "            </div>";
    rowItem += "        </div>";
    rowItem += "        <div class='form-group pt-3'>";
    rowItem += "            <div class='form-inline' role='form'>";
    rowItem += "                <div class='container'>";
    rowItem += "                    <div class='row'>";
    rowItem += "                        <div class='col-sm align-self-center'>";
    rowItem += "                            <span>left</span>";
    rowItem += "                        </div>";
    rowItem += "                        <div class='col-sm align-self-center'>";
    rowItem += "                            <input type='text' id='Config_Composer_Left' class='form-control' style='width:70%' name='left' oninput=this.value=this.value.replace(/[^0-9.]/g,'').replace(/(\\..*)\\./g,'$1'); required>";
    rowItem += "                        </div>";
    rowItem += "                        <div class='col-sm align-self-center'>";
    rowItem += "                            <span>top</span>";
    rowItem += "                        </div>";
    rowItem += "                        <div class='col-sm align-self-center'>";
    rowItem += "                            <input type='text' id='Config_Composer_Top' class='form-control' style='width:70%' name='top' oninput=this.value=this.value.replace(/[^0-9.]/g,'').replace(/(\\..*)\\./g,'$1'); required>";
    rowItem += "                        </div>";
    rowItem += "                        <div class='col-sm align-self-center'>";
    rowItem += "                            <span>right</span>";
    rowItem += "                        </div>";
    rowItem += "                        <div class='col-sm align-self-center'>";
    rowItem += "                            <input type='text' id='Config_Composer_Right' class='form-control' style='width:70%' name='right' oninput=this.value=this.value.replace(/[^0-9.]/g,'').replace(/(\\..*)\\./g,'$1'); required>";
    rowItem += "                        </div>";
    rowItem += "                        <div class='col-sm align-self-center'>";
    rowItem += "                            <span>bottom</span>";
    rowItem += "                        </div>";
    rowItem += "                        <div class='col-sm align-self-center'>";
    rowItem += "                            <input type='text' id='Config_Composer_Bottom' class='form-control' style='width:70%' name='bottom' oninput=this.value=this.value.replace(/[^0-9.]/g,'').replace(/(\\..*)\\./g,'$1'); required>";
    rowItem += "                        </div>";
    rowItem += "                    </div>";
    rowItem += "                </div>";
    rowItem += "            </div>";
    rowItem += "        </div>";
    rowItem += "    </div>";
    rowItem += "</div>";

    let subIndexCnt = $(obj).closest('.index').find('.subindex');
    // console.log($(obj).closest('.Composer_Item'));
    if(subIndexCnt.length < 13){
        $(obj).closest('.index').find('.Composer_Item').append(rowItem); // 동적으로 row를 추가한다.
    }
}

function delSubRow(obj){

    let index= $(obj).closest('.subindex');
    let subIndexCnt = $(obj).closest('.index').find('.subindex');

    if(subIndexCnt.length > 1){
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
        url: "/dashboard/config/composer/chk.do",
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
                    $('Form[name=Insert_Composer]').submit();
            }
        }
    })
    return false;
}

function chk_Value(){

    for(let i=0; i<$('.index').length; i++){
        if($('.item_id').eq(i).val()=="") {
            $('.item_id').eq(i).focus();
            alert("id를 입력해주세요.");
            exit = true;
            return false;
        }else if($('.item_width').eq(i).val()==""){
            $('.item_width').eq(i).focus();
            alert("width를 입력해주세요.");
            exit = true;
            return false;
        }else if($('.item_height').eq(i).val()==""){
            $('.item_height').eq(i).focus();
            alert("height를 입력해주세요.");
            exit = true;
            return false;
        }

        for(let j=0; j<$('.subindex').length; j++){
            if($('input[name=left]').eq(j).val()==""){
                $('input[name=left]').eq(j).focus();
                alert("left를 입력해주세요.");
                exit = true;
                return false;
            }else if($('input[name=top]').eq(j).val()==""){
                $('input[name=top]').eq(j).focus();
                alert("top를 입력해주세요.");
                exit = true;
                return false;
            }else if($('input[name=right]').eq(j).val()==""){
                $('input[name=right]').eq(j).focus();
                alert("right를 입력해주세요.");
                exit = true;
                return false;
            }else if($('input[name=bottom]').eq(j).val()==""){
                $('input[name=bottom]').eq(j).focus();
                alert("bottom를 입력해주세요.");
                exit = true;
                return false;
            }
        }

        for(let j=0; j<i; j++){
            if ($('.item_id').eq(i).val() == $('.item_id').eq(j).val()) {
                $('.item_id').eq(i).focus();
                alert("id가 중복입니다.");
                exit=true;
                return false;
            }
        }

    }
    exit=false;
}