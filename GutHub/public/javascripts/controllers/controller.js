/**
 * Created by wanghui21 on 2015/4/4.
 */


var app = angular.module('guthub',
    ['guthub.directives','guthub.services']);

app.config(['$routeProvider',function($routeProvider){
    $routeProvider.
        when('/',{
            controller:'ListCtrl',
            resolve:{
                recipes:function(MultiRecipeLoader){
                    return MultiRecipeLoader();
                }
            },
            templateUrl:'/views/list.html'
        }).
        when('/edit/:recipeId',{
            controller:'EditCtrl',
            resolve: {
                recipe: function (RecipeLoader) {
                    return RecipeLoader();
                }
            },
            templateUrl:'/views/recipeForm.html'
        }).
        when('/view/:recipeId',{
            controller:'ViewCtrl',
            resolve: {
                recipe: function(RecipeLoader){
                    return RecipeLoader();
                }
            },
            templateUrl:'/views/viewRecipe.html'
        }).
        when('/new',{
            controller:'NewCtrl',
            templateUrl:'/views/recipeForm.html'
        }).
        otherwise({redirectTo:'/'});
}]);


app.controller('ListCtrl', ['$scope', 'recipes',
    function($scope, recipes) {
        $scope.recipes = recipes;
    }]);

app.controller('ViewCtrl',['$scope','$location','recipe',
    function($scope,$location,recipe){
        $scope.recipe = recipe;
        $scope.edit = function(){
          $location.path('/edit/'+recipe._id);
        };

        $scope.remove = function(){
            delete  $scope.recipe;
            $location.path('/');
        }
    }]);

app.controller('EditCtrl',['$scope','$location','recipe',
    function($scope,$location,recipe){
        $scope.recipe = recipe;

        $scope.save = function(){
            $scope.recipe.$save(function(recipe){
                $location.path('/view/'+recipe._id);
            });
        };
        $scope.remove = function(){
            delete  $scope.recipe;
            $location.path('/');
        }
    }]);

app.controller('NewCtrl',['$scope','$location','Recipe',
    function($scope,$location,Recipe){
        $scope.recipe = new Recipe({
            ingredients: [{}]
        });

        $scope.save = function(){
            $scope.recipe.$save(function (recipe) {
                $location.path('/');
            })
        };
    }]);

app.controller('IngredientsCtrl', ['$scope',
    function($scope) {
        $scope.addIngredient = function() {
            var ingredients = $scope.recipe.ingredients;
            ingredients[ingredients.length] = {};
        };
        $scope.removeIngredient = function(index) {
            $scope.recipe.ingredients.splice(index, 1);
        };
    }]);


//test
function TestCtrl($scope) {
    $scope.mytest = 'test';
}
var testApp = angular.module('testhub',[]);

testApp.config(['$routeProvider',function($routeProvider){
    $routeProvider.
        when('/',{
            controller:'TestCtrl',
            templateUrl:'views/list.html'
        }).
        otherwise({redirectTo:'/'});
}]);