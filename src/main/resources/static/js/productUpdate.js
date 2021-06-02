//加
function addProduct(obj){
    //获取所有按钮下标
    let index = $(".car_btn_2").index(obj);
    let stock = parseInt($(".car_ipt").eq(index).val());
    stock++;
    let id = parseInt($(".p_id").eq(index).val());
    //将stock传入后台,动态更新数据库
    $.ajax({
        type:"POST",
        url:"/product/updateProduct/"+id+"/"+stock
    });
    $(".car_ipt").eq(index).val(stock);
}
//减
function subProduct(obj){
    let index = $(".car_btn_1").index(obj);
    let stock = parseInt($(".car_ipt").eq(index).val());
    stock--;
    if (stock<0){
        alert("没有东西了，再减下去就要破产了");
        return false;
    }
    let id = parseInt($(".p_id").eq(index).val());
    $.ajax({
        type: "post",
        url: "/product/updateProduct/"+id+"/"+stock
    })
    $(".car_ipt").eq(index).val(stock);
}
function updatePrice(obj){
    var index =$(".p_upp").index(obj);
    var price = parseFloat($(".p_pric").eq(index).val());
    var id = parseInt($(".p_id").eq(index).val());
    if (confirm("确认修改？")){
        $.ajax({
            type:"POST",
            url:"/product/updatePrice/"+id+"/"+price
        })
    }
}