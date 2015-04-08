var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');

var routes = require('./routes/index');
var users = require('./routes/users');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

// uncomment after placing your favicon in /public
//app.use(favicon(__dirname + '/public/favicon.ico'));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', routes);
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
