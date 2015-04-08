/**
 * Created by wanghui21 on 2015/4/8.
 */

var mongoose = require("mongoose");
var ingredient = new mongoose.Schema({
    amount:String,
    amountUnits:String,
    ingredientName:String
});

exports.Recipeschema = new mongoose.Schema({
    title:String,
    ingredients:[ingredient],
    instructions:String
});
