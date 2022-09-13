$(document).ready(function() {
    $("#Go_Roles").addClass("active");

});

$(document).on("click",".btn-modal",function() {
    let data = $(this).data('id');
    $('input[name=which_data]').val(data);
});

// $(".btn-modal").click(function(){
//     let data = $(this).data('id');
//     $('input[name=which_data]').val(data);
// });

function Config_Roles_Add_On(){
    $('#Configure_Roles').hide();
    $('#Configure_Roles_Add').show();
}

// input_streams 값 가져오기
function putInputStreams(){

    $('div[name=Roles_1_2]').remove();
    $('div[name=Roles_1_3]').remove();

    let group_id = $('select[name=input_group_id]').val();

    $.ajax({
        url: "/dashboard/config/roles/putInputStreams.do",
        data: {
            group_id:group_id
        },
        dataType: "text",
        type:"POST",
        success: function(data) {
            $('input[name=input_streams]').val(data);
            ViewProcess(data);

        }
    })
}

function ViewProcess(num){
    switch(num){
        case '4':
        case '5':
        case '6':
            addProcess(1,2);
            addProcess(1,3);
            // addProcess(2,2);
            // addProcess(2,3);
            break;
        case '3':
        case '7':
            addProcess(1,2);
            break;
        default:
            break;
    }
}

function Select_Packagers(){

    let checked = $('input[name=getListPackager]:checked');
    let text = "";

    $('select[name=packager_id_1] option').not("[value='선택']").remove()
    $('select[name=packager_id_2] option').not("[value='선택']").remove()

    if(checked.length>2){
        alert("최대 2개까지 선택이 가능합니다.");
        $(checked).prop("checked", false);
        $('input[name=packager_group_id]').val('');
        return false;
    }

    for(let i=0; i<checked.length; i++){
        text += checked.eq(i).val();
        $('select[name=packager_id_1]').append("<option value='"+checked.eq(i).val()+"'>" +checked.eq(i).val()+"</option>");
        $('select[name=packager_id_2]').append("<option value='"+checked.eq(i).val()+"'>" +checked.eq(i).val()+"</option>");
        if(i<checked.length-1){
            text += ",";
        }
    }
    $('input[name=packager_group_id]').val(text);

    $(checked).prop("checked", false);
}

function addRow(obj){

    let rowItem = "<div class='index pb-3'>";
    rowItem += "<div class='border'>";
    rowItem += "<div class='col text-right pt-2 mr-2'>";
    rowItem += "    <button type='button' class='close' onclick='delRow(this)'><span aria-hidden='true'>×</span>";
    rowItem += "    </button>";
    rowItem += "</div>";
    rowItem += "<div class='container-fluid'>";
    rowItem += "            <div class='form-group pt-3'>";
    rowItem += "                    <div class='form-inline'>";
    rowItem += "                        <div class='col-3'>";
    rowItem += "                            <span>ID</span>";
    rowItem += "                        </div>";
    rowItem += "                        <div class='col'>";
    rowItem += "                            <input type='text' name='item_id' class='form-control splitvalue' required>";
    rowItem += "                        </div>";
    rowItem += "                    </div>";
    rowItem += "                </div>";
    rowItem += "                <div class='form-group pt-3'>";
    rowItem += "                    <div class='form-inline'>";
    rowItem += "                        <div class='col-3'>";
    rowItem += "                            <span>Source ID</span>";
    rowItem += "                        </div>";
    rowItem += "                        <div class='col'>";
    rowItem += "                            <select class='form-select form-select-sm form-control w-100' name='roles_source_id'>";
    rowItem += "                                <option value='선택'> 선택</option>";
    rowItem += "                            </select>";
    rowItem += "                        </div>";
    rowItem += "                    </div>";
    rowItem += "                </div>";
    rowItem += "                <div class='form-group pt-2'>";
    rowItem += "                    <div class='form-inline'>";
    rowItem += "                        <div class='col-3'>";
    rowItem += "                            <span>Resizer ID</span>";
    rowItem += "                        </div>";
    rowItem += "                        <div class='col'>";
    rowItem += "                            <select class='form-select form-select-sm form-control w-100' name='roles_resizer_id'>";
    rowItem += "                                <option value='선택'> 선택</option>";
    rowItem += "                            </select>";
    rowItem += "                        </div>";
    rowItem += "                    </div>";
    rowItem += "                </div>";
    rowItem += "            </div>";
    rowItem += "        </div>";
    rowItem += "    </div>";

    if($('.index').length<12){ // 총 12개 까지만 생성
        if(obj != undefined){
            $(obj).closest('.card-body').find('.Process_Body').append(rowItem); // 동적으로 row를 추가한다.
        }else{
            $('.Process_Body').append(rowItem);
        }
    }


    getSourceId($(obj).closest('.card-body').find('.index').length);
    getResizerId($(obj).closest('.card-body').find('.index').length);
}
function addProcess(id,row){

    if(id!=2){
        id = '';
    }

    let rowItem = "<div class='form-group' name='Roles"+id+"_1_"+row+"'>";
    rowItem += "    <div class='form-inline'>";
    rowItem += "        <div class='col-3'>";
    rowItem += "            <span class=''>ID 1+"+row+" Grid Process</span>";
    rowItem += "            <input type='text' name='Process"+id+"_1_"+row+"' class='form-control w-25 ' readOnly required>";
    rowItem += "            <input type='hidden' name='Source"+id+"_1_"+row+"' class='form-control' required>";
    rowItem += "            <input type='hidden' name='Resize"+id+"_1_"+row+"' class='form-control' required>";
    rowItem += "            <a data-id='"+id+"_1_"+row+"' data-toggle='modal' data-target='#Configure_Roles_Grid_Processer' data-backdrop='static' onClick='getProcess(this)' class='btn btn-info btn-user btn-modal'>선택</a>";
    rowItem += "        </div>";
    rowItem += "        <div class='col-3'>";
    rowItem += "            <span>Composer</span>";
    rowItem += "            <input type='text' name='Composer"+id+"_1_"+row+"' class='form-control w-25' readOnly required>";
    rowItem += "                <a data-id='"+id+"_1_"+row+"' data-toggle='modal' data-target='#Configure_Roles_Composer' data-backdrop='static' onClick='getComposerId(this)' class='btn btn-info btn-user btn-modal'>선택</a>";
    rowItem += "        </div>";
    rowItem += "        <div class='col-3'>";
    rowItem += "            <span>abr_resizer</span>";
    rowItem += "            <input type='text' name='Abr"+id+"_1_"+row+"' class='form-control w-25' readOnly required>";
    rowItem += "            <a data-id='"+id+"_1_"+row+"' data-toggle='modal' data-target='#Configure_Roles_Abr' data-backdrop='static' onClick='getAbrResizerId(this)' class='btn btn-info btn-user btn-modal'>선택</a>";
    rowItem += "        </div>";
    rowItem += "        <div class='col-3'>";
    rowItem += "            <span>video</span>";
    rowItem += "            <input type='text' name='Video"+id+"_1_"+row+"' class='form-control w-25' readOnly required>";
    rowItem += "                <a data-id='"+id+"_1_"+row+"' data-toggle='modal' data-target='#Configure_Roles_Video' data-backdrop='static' onClick='getVideoId(this)' class='btn btn-info btn-user btn-modal'>선택</a>";
    rowItem += "        </div>";
    rowItem += "    </div>";
    rowItem += "</div>";

    if(id!=2){
        $('.Roles_Row').append(rowItem);
    }else{
        $('.Roles2_Row').append(rowItem);
    }
}

function delRow(obj){

    let index= $(obj).closest('.index');
    let indexCnt = $(obj).closest('.Process_Body').find('.index');

    if(indexCnt.length != 1){
        index.remove();  // 동적으로 row를 삭제한다.
    }
}

function getProcess(obj){

    $('.index').remove();

    let data = $(obj).data('id');

    if($('input[name=Process'+data+']').val()!=""){
        CallProcessData(data);
    }else{
        addRow();
        getSourceId();
        getResizerId();
    }
}

function CallProcessData(data){

    let process = $('input[name=Process'+data+']').val().split(',');
    let source = $('input[name=Source'+data+']').val().split(',');
    let resize = $('input[name=Resize'+data+']').val().split(',')

    for(let i=0; i<process.length; i++){

        addRow($('#addProcess'));

        $('input[name=item_id]').eq(i).val(process[i]);
        $('select[name=roles_source_id]').eq(i).val(source[i]);
        $('select[name=roles_resizer_id]').eq(i).val(resize[i]);
    }

}

function getSourceId(index){

    if(index==undefined){
        index = 1;
    }

    $.ajax({
        url: "/dashboard/config/roles/getSourceId.do",
        data: {
            input_id :$('select[name=input_group_id]').val()
        },
        type:"POST",
        async: false,
        success: function(data) {

            $('select[name=roles_source_id]').eq(index-1).empty();
            $('select[name=roles_source_id]').eq(index-1).append($('<option>', {
                value: '선택',
                text:  '선택'
            }));
            for (let i in data) {
                $('select[name=roles_source_id]').eq(index-1).append($('<option>', {
                    value: data[i].id,
                    text:  data[i].id
                }));
            }
        }
    })
}

function getResizerId(index){

    if(index==undefined){
        index = 1;
    }

    $.ajax({
        url: "/dashboard/config/roles/getResizerId.do",
        data: {
            resize_id :$('select[name=resize_group_id]').val()
        },
        type:"POST",
        async: false,
        success: function(data) {

            $('select[name=roles_resizer_id]').eq(index-1).empty();
            $('select[name=roles_resizer_id]').eq(index-1).append($('<option>', {
                value: '선택',
                text:  '선택'
            }));
            for (let i in data) {
                $('select[name=roles_resizer_id]').eq(index-1).append($('<option>', {
                    value: data[i].id,
                    text:  data[i].id
                }));
            }

        }
    })
}

function confirmProcessData(obj){

    let id = "";
    let source_id = "";
    let resizer_id = "";
    let which_data = $('input[name=which_data]').val();

    for(let i=0; i<$(obj).closest('.card-body').find('.index').length; i++){
        id += $(obj).closest('.card-body').find('input[name=item_id]').eq(i).val();
        source_id += $(obj).closest('.card-body').find('select[name=roles_source_id]').eq(i).val();
        resizer_id += $(obj).closest('.card-body').find('select[name=roles_resizer_id]').eq(i).val();

        if(i<$(obj).closest('.card-body').find('.index').length-1){
            id +=",";
            source_id +=",";
            resizer_id +=",";
        }
    }
    for(let i=0; i<$(obj).closest('.card-body').find('.index').length; i++){

        if($(obj).closest('.card-body').find('input[name=item_id]').eq(i).val()==""){
            $(obj).closest('.card-body').find('input[name=item_id]').eq(i).focus();
            alert("ID를 입력해주세요.");
            return false;
        }else if($(obj).closest('.card-body').find('select[name=roles_source_id]').eq(i).val()=="선택"){
            $(obj).closest('.card-body').find('select[name=roles_source_id]').eq(i).focus();
            alert("Source ID 입력해주세요.");
            return false;
        }else if($(obj).closest('.card-body').find('select[name=roles_resizer_id]').eq(i).val()=="선택"){
            $(obj).closest('.card-body').find('select[name=roles_resizer_id]').eq(i).focus();
            alert("Resizer ID 입력해주세요.");
            return false;
        }
    }

    $('input[name=Process'+which_data+']').val(id);
    $('input[name=Source'+which_data+']').val(source_id);
    $('input[name=Resize'+which_data+']').val(resizer_id);
}

function getComposerId(obj){

    let Composer_checkbox = "";
    let data_size = 0;
    let data = $(obj).data('id');

    $.ajax({
        url: "/dashboard/config/roles/getComposerId.do",
        data: {
            composer_id :$('select[name=grid_composer_group_id]').val()
        },
        type:"POST",
        async: false,
        success: function(data) {
            $('.composer').empty();
            for (let i in data) {
                data_size = data.length;
                Composer_checkbox +="<div class='form-check'>";
                Composer_checkbox +="<input type='checkbox' value='"+data[i].id+"' name='composer_id' class='form-check-input'>";
                Composer_checkbox +="<label class='form-check-label'>"+data[i].id+"</label>";
                Composer_checkbox +="</div>";
            }
            $('.composer').append(Composer_checkbox);
        }
    })

    if($('input[name=Composer'+data+']').val()!=""){
        let composer = $('input[name=Composer'+data+']').val().split(',');

        for ( let i=0; i<data_size; i++ ) {
            for(let j=0; j<composer.length; j++){
                if($('input[name=composer_id]').eq(i).val()==composer[j]){
                    $('input[name=composer_id]').eq(i).prop('checked', true);
                }
            }
        }
    }
}

function confirmComposerData(){

    let which_data = $('input[name=which_data]').val();
    let checked_composer = $('input[name=composer_id]:checked');
    let composer_text = "";
    for(let i=0; i<checked_composer.length; i++){

        composer_text += checked_composer.eq(i).val();
        if(i<checked_composer.length-1){
            composer_text += ",";
        }
    }
    $('input[name=Composer'+which_data+']').val(composer_text);
}

function getAbrResizerId(obj){

    let Abr_checkbox = "";
    let data_size = 0;
    let data = $(obj).data('id');

    $.ajax({
        url: "/dashboard/config/roles/getAbrResizerId.do",
        data: {
            abr_id :$('select[name=abr_resizer_group_id]').val()
        },
        type:"POST",
        async: false,
        success: function(data) {
            $('.abr').empty();
            for (let i in data) {
                data_size = data.length;
                Abr_checkbox +="<div class='form-check'>";
                Abr_checkbox +="<input type='checkbox' value='"+data[i].id+"' name='abr_id' class='form-check-input'>";
                Abr_checkbox +="<label class='form-check-label'>"+data[i].id+"</label>";
                Abr_checkbox +="</div>";
            }
            $('.abr').append(Abr_checkbox);
        }
    })

    if($('input[name=Abr'+data+']').val()!=""){
        let abr = $('input[name=Abr'+data+']').val().split(',');

        for ( let i=0; i<data_size; i++ ) {
            for(let j=0; j<abr.length; j++){
                if($('input[name=abr_id]').eq(i).val()==abr[j]){
                    $('input[name=abr_id]').eq(i).prop('checked', true);
                }
            }
        }
    }
}

function confirmAbrData(){

    let which_data = $('input[name=which_data]').val();
    let checked_abr = $('input[name=abr_id]:checked');
    let abr_text = "";
    for(let i=0; i<checked_abr.length; i++){

        abr_text += checked_abr.eq(i).val();
        if(i<checked_abr.length-1){
            abr_text += ",";
        }
    }
    $('input[name=Abr'+which_data+']').val(abr_text);
}

function getVideoId(obj){

    let Video_checkbox = "";
    let data_size = 0;
    let data = $(obj).data('id');

    $.ajax({
        url: "/dashboard/config/roles/getVideoId.do",
        data: {
            video_id :$('select[name=encoder_group_id]').val()
        },
        type:"POST",
        async: false,
        success: function(data) {
            $('.video').empty();
            for (let i in data) {
                data_size = data.length;

                Video_checkbox +="<div class='form-check'>";
                Video_checkbox +="<input type='checkbox' value='"+data[i].id+"' name='video_id' class='form-check-input'>";
                Video_checkbox +="<label class='form-check-label'>"+data[i].id+"</label>";
                Video_checkbox +="</div>";
            }
            $('.video').append(Video_checkbox);
        }
    })

    if($('input[name=Video'+data+']').val()!=""){
        let video = $('input[name=Video'+data+']').val().split(',');

        for ( let i=0; i<data_size; i++ ) {
            for(let j=0; j<video.length; j++){
                if($('input[name=video_id]').eq(i).val()==video[j]){
                    $('input[name=video_id]').eq(i).prop('checked', true);
                }
            }
        }
    }
}

function confirmVideoData(){

    let which_data = $('input[name=which_data]').val();
    let checked_video = $('input[name=video_id]:checked');
    let video_text = "";
    for(let i=0; i<checked_video.length; i++){

        video_text += checked_video.eq(i).val();
        if(i<checked_video.length-1){
            video_text += ",";
        }
    }
    $('input[name=Video'+which_data+']').val(video_text);
}

//숫자만 입력받도록 한다.
function inNumber(){
    if(event.keyCode<48 || event.keyCode>57){
        event.returnValue=false;
    }
}

function chk_GroupId(){

    let id = $('input[name=id]').val();

    $.ajax({
        url: "/dashboard/config/roles/chk.do",
        data: {
            id:id
        },
        type:"POST",
        success: function(data) {
            if(data.length!=0){
                alert('group_id가 중복입니다.');
            }else{
                if($('input[name=id]').val()==""){
                    $('input[name=id]').focus();
                    alert("id를 입력해주세요.");
                    return false;
                }else if($('select[name=input_group_id]').val()=="선택"){
                    $('select[name=input_group_id]').focus();
                    alert("input Group ID를 선택해주세요.");
                    return false;
                }else if($('select[name=grid_composer_group_id]').val()=="선택"){
                    $('select[name=grid_composer_group_id]').focus();
                    alert("Compsoer를 선택해주세요.");
                    return false;
                }else if($('input[name=packager_group_id]').val()==""){
                    $('input[name=packager_group_id]').focus();
                    alert("Packager Group ID를 선택해주세요.");
                    return false;
                }else if($('select[name=encoder_group_id]').val()=="선택"){
                    $('select[name=encoder_group_id]').focus();
                    alert("Video Group ID를 선택해주세요.");
                    return false;
                }else if($('select[name=resize_group_id]').val()=="선택"){
                    $('select[name=resize_group_id]').focus();
                    alert("Resize Group ID를 선택해주세요.");
                    return false;
                }else if($('select[name=abr_resizer_group_id]').val()=="선택"){
                    $('select[name=abr_resizer_group_id]').focus();
                    alert("Abr Resize Group ID를 선택해주세요.");
                    return false;
                }else if($('input[name=preset_group_id_1]').val()==""){
                    $('input[name=preset_group_id_1]').focus();
                    alert("Preset Group ID를 입력해주세요.");
                    return false;
                }else if($('select[name=packager_id_1]').val()=="선택"){
                    $('select[name=packager_id_1]').focus();
                    alert("Packager ID를 선택해주세요.");
                    return false;
                }else if($('input[name=preset_group_id_2]').val()==""){
                    $('input[name=preset_group_id_2]').focus();
                    alert("Preset Group ID를 입력해주세요.");
                    return false;
                }else if($('select[name=packager_id_2]').val()=="선택"){
                    $('select[name=packager_id_2]').focus();
                    alert("Packager ID를 선택해주세요.");
                    return false;
                }else if($('input[name=Process_1_1]').val()==""){
                    $('input[name=Process_1_1]').focus();
                    alert("1+1 Gird Process를 선택해주세요.");
                    return false;
                }else if($('input[name=Process2_1_1]').val()==""){
                    $('input[name=Process2_1_1]').focus();
                    alert("Thumbnail Gird Process를 선택해주세요.");
                    return false;
                }else if($('input[name=Process_1_2]')){
                    if($('input[name=Process_1_2]').val()==""){
                        $('input[name=Process_1_2]').focus();
                        alert("1+2 Gird Process를 선택해주세요.");
                        return false;
                    }
                }else if($('input[name=Process_1_3]')){
                    if($('input[name=Process_1_3]').val()==""){
                        $('input[name=Process_1_3]').focus();
                        alert("1+3 Gird Process를 선택해주세요.");
                        return false;
                    }
                }

                $('Form[name=Insert_Roles]').submit();
            }
        }
    })
    return false;
}

$(document).on("mouseover","input",function(e) { // mouseover event
    $(this).mousemove(function(e) {
        if($(this).val()!=""){
            $('#divLayer #detail_value').empty().append($(this).val());
            let t=e.pageY-15;
            let l=e.pageX+20;
            $('#divLayer').css({"top":t, "left":l,"position":"absolute","opacity":"0,8" }).show();
        }
    });
});

$(document).on("mouseout","input",function() {
    $('#divLayer').hide();
});
