/**
 * Created by wanghui21 on 2015/4/27.
 */
var mongoose = require("mongoose");

exports.AccountSchema = new mongoose.Schema({
    name:String,
    pwd:String
});