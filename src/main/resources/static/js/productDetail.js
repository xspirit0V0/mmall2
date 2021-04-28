$(function(){
    //给type绑定点击事件
    $(".type").click(function () {
        var index = $(".type").index(this);
        var obj = $(".type").eq(index);
        $(".type").removeClass("checked");
        obj.addClass("checked");
    });
    //给color绑定点击事件
    $(".color").click(function () {
        var index = $(".color").index(this);
        var obj = $(".color").eq(index);
        $(".color").removeClass("checked");
        obj.addClass("checked");
    });
});

//商品数量++
function increase() {
    var value = $("#quantity").val();
    var stock = $("#stock").text();
    value++;
    if(value > stock){
        value = stock;
    }
    $("#quantity").val(value);
}

//商品数量--
function reduce() {
    let quantity = $("#quantity").val();
    if(quantity > 1){
        quantity--;
    }
    $("#quantity").val(quantity);
}

//添加购物车
function addCart(productId,price){
    let stockStr = $("#stock").text();
    let stock = parseInt(stockStr);
    if (stock==0){
        alert("库存不足!")
        return false;
    }
    let quantity = $("#quantity").val();
    window.location.href="/cart/add/"+productId+"/"+price+"/"+quantity;
}