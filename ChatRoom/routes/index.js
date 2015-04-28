var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

var mongoose = require('mongoose');
var db = mongoose.createConnection('localhost', 'chatroomapp');
var AccountSchema = require('../models/Account.js').AccountSchema;
var Account = db.model('chatroom11', AccountSchema);

router.create = function(req,res){
    var reqBody = req.body,
        reqObject = {name:reqBody.name,pwd:reqBody.pwd};
    var accountUser = new Account(reqObject);
    Account.findOne({name:reqBody.name},function(err,user){
        if (err)
            return res.json({err:err});
        if (user) {
            return res.json({err:"用户名已经存在"});
        }
        accountUser.save(function(err,doc){
            if(err || !doc){
                throw 'Error'
            } else{
                res.json(doc);
            }
        });
    });
};

router.login = function (req,res) {
    Account.findOne({name:req.body.name},function(err,user){
        if(err){
            return res.json({err:err});
        }
        if(!user){
            return res.json({err:"用户名不存在"});
        }
        if(user.pwd!=req.body.pwd){
            return res.json({err:"密码错误"});
        }
        res.json(user);
    });
};

module.exports = router;
