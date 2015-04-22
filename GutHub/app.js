var express = require('express');//加载express模块
var path = require('path');//文件路径模块
var favicon = require('serve-favicon');//网页图标模块
var logger = require('morgan');//logger模块
var cookieParser = require('cookie-parser');//解析来自浏览器的cookie
var bodyParser = require('body-parser');//读取并解析请求体，放到req.body中

var routes = require('./routes/index');
var users = require('./routes/users');

var app = express();//生成express实例

// view engine setup
// 设置views路径和模板
//__dirname是node.js里面的全局变量，即取得执行的js所在的路径，
// 另外__dirname是目前执行的js文件名。所以，app.set(‘views’, __dirname + ‘/views’);
// 是设置views的文件//夹。
app.set('views', path.join(__dirname, 'views')); //设置view文件夹
//设置express.js所使用的render engine。除了Jade之外，
// express.js还支持EJS(embedded javascript)、
// Haml、CoffeScript和jQuery template等js模板。
app.set('view engine', 'jade'); //设置模板

// uncomment after placing your favicon in /public
//app.use(favicon(__dirname + '/public/favicon.ico'));
app.use(logger('dev'));//输出有颜色区分的日志，以便于调试开发
app.use(bodyParser.json());//bodyParser()是Connect內建的middleware，设置此处可以将client提交过来的post请求放入request.body中。
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
//提供./public下的静态文件
app.use(express.static(path.join(__dirname, 'public')));//express.static()也是一个Connect內建的middleware来处理静态的requests，例如css、js、img文件等。所以static()里面指定的文件夹中的文件会直接作为静态资源吐出来。


app.use('/', routes);//指定程序路由
app.use('/users', users);

var recipes_map = {
    '1': {
        "id": "1",
        "title": "Cookies",
        "description": "Delicious, crisp on the outside, chewy on the outside, oozing with chocolatey goodness cookies. The best kind",
        "ingredients": [
            {
                "amount": "1",
                "amountUnits": "packet",
                "ingredientName": "Chips Ahoy"
            }
        ],
        "instructions": "1. Go buy a packet of Chips Ahoy\n2. Heat it up in an oven\n3. Enjoy warm cookies\n4. Learn how to bake cookies from somewhere else"
    },
    '2': {
        id: 2,
        'title': 'Recipe 2',
        'description': 'Description 2',
        'instructions': 'Instruction 2',
        ingredients: [
            {amount: 13, amountUnits: 'pounds', ingredientName: 'Awesomeness'}
        ]
    }
};
var next_id = 3;
app.get('/recipes',routes.list);
app.get('/recipes/:id',routes.recipe);
app.post('/recipes',routes.create);
/*app.get('/recipes', function(req, res) {
    var recipes = [];

    for (var key in recipes_map) {
        recipes.push(recipes_map[key]);
    }

    // Simulate delay in server
    setTimeout(function() {
        res.send(recipes);
    }, 500);
});

app.get('/recipes/:id', function(req, res) {
    console.log('Requesting recipe with id', req.params.id);
    res.send(recipes_map[req.params.id]);
});

app.post('/recipes', function(req, res) {
    var recipe = {};
    recipe.id = next_id++;
    recipe.title = req.body.title;
    recipe.description = req.body.description;
    recipe.ingredients = req.body.ingredients;
    recipe.instructions = req.body.instructions;

    recipes_map[recipe.id] = recipe;

    res.send(recipe);
});

app.post('/recipes/:id', function(req, res) {
    var recipe = {};
    recipe.id = req.params.id;
    recipe.title = req.body.title;
    recipe.description = req.body.description;
    recipe.ingredients = req.body.ingredients;
    recipe.instructions = req.body.instructions;

    recipes_map[recipe.id] = recipe;

    res.send(recipe);
});*/

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});

// error handlers

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
  app.use(function(err, req, res, next) {
    res.status(err.status || 500);
    res.render('error', {
      message: err.message,
      error: err
    });
  });
}

// production error handler
// no stacktraces leaked to user
app.use(function(err, req, res, next) {
  res.status(err.status || 500);
  res.render('error', {
    message: err.message,
    error: {}
  });
});


module.exports = app;
