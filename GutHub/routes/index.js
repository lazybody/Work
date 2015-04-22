var express = require('express');
var router = express.Router();

/* GET home page. */
//上面的代码意思是，get请求根目录则调用views文件夹中的index模板，
// 并且传入参数title为“Express”，这个title就可以在模板文件中直接使用。
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});


var mongoose = require('mongoose');
var db = mongoose.createConnection('localhost', 'guthubapp');
var Recipeschema = require('../models/Recipe.js').Recipeschema;
var Recipe = db.model('guthub', Recipeschema);
router.list = function(req, res) {
    Recipe.find({}, 'title', function(error, guthub) {
        res.json(guthub);
    });
};

router.recipe = function(req, res) {
    var recipeId = req.params.id;
    Recipe.findById(recipeId, '', { lean: true }, function(err, guthub) {
        if(guthub) {
            /*var title,
                ingredients,
                instructions = 0;
            for(c in poll.choices) {
                var choice = poll.choices[c];
                for(v in choice.votes) {
                    var vote = choice.votes[v];
                    totalVotes++;
                    if(vote.ip === (req.header('x-forwarded-for') || req.ip)) {
                        userVoted = true;
                        userChoice = { _id: choice._id, text: choice.text };
                    }
                }
            }
            poll.userVoted = userVoted;
            poll.userChoice = userChoice;
            poll.totalVotes = totalVotes;*/
            res.json(guthub);
        } else {
            res.json({error:true});
        }
    });
};

router.create = function(req, res) {
    var reqBody = req.body,
        ingredients = reqBody.ingredients.filter(function(v) { return v.text != ''; }),
        recipeObj = {title: reqBody.title,instructions:reqBody.instructions, ingredients: ingredients};
    var recipe = new Recipe(recipeObj);
    recipe.save(function(err, doc) {
        if(err || !doc) {
            throw 'Error';
        } else {
            res.json(doc);
        }
    });
};

module.exports = router;
