/**
 * Created by wanghui21 on 2015/4/13.
 */
var app = angular.module('chatroom',[]);

app.config(['$routeProvider',
    function($routeProvider){
        $routeProvider.
            when('/',{
                controller:'LoginCtrl',
                templateUrl:'/views/login.html'
            }).
            when('/room',{
                controller:'RoomCtrl',
                templateUrl:'/views/room.html'
            }).
            otherwise({redirectTo:'/'});
    }]);

app.controller("LoginCtrl",['$scope','$location',
    function($scope,$location){
        $scope.account = {name:'wang@163.com',pwd:'111111'};
        $scope.login = function(){
            if($scope.name.length<=0){

            }else {
                $location.path('/room');
            }
        };
    }]);

app.controller("RoomCtrl",['$scope',
    function($scope){

    }]);