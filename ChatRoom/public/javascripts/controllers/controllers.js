/**
 * Created by wanghui21 on 2015/4/13.
 */
var app = angular.module('chatroom',['chatroom.services']);

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
            when('/reg',{
                controller:'RegCtrl',
                templateUrl:'/views/register.html'
            }).
            otherwise({redirectTo:'/'});
    }]);

app.controller("LoginCtrl",['$scope','$location','$http',
    function($scope,$location,$http){
        $scope.account = {name:'',pwd:''};
        $scope.loginerro ;
        $scope.login = function(){
            if(!$scope.account.name){
                $scope.loginerro = '用户名不能为空';
            }else if(!$scope.account.pwd.length){
                $scope.loginerro = '密码不能为空';
            }else {
                $http.post('/login',$scope.account).success(function(data){
                    if(data.err){
                        $scope.loginerro = data.err;
                        return;
                    }else {
                        $location.path('/room');
                    }
                });
            }
        };
        $scope.reg = function(){
            $location.path('/reg');
        };
    }]);

app.controller("RegCtrl",['$scope','$location','$http',
    function($scope,$location,$http){
        $scope.rep_pwd = "";
        $scope.account1={name:'',pwd:''};
        $scope.submitReg = function() {
            if(!$scope.account1.name){
                $scope.err = "用户名不能为空";
                return;
            }else if(!$scope.account1.pwd){
                $scope.err = "密码不能为空";
                return;
            }else if($scope.account1.pwd != $scope.rep_pwd){
                $scope.err = "两次密码不一致";
                return;
            }
            $http.post('/signup',$scope.account1).success(function(data){
                if(data.err){
                    $scope.err = data.err;
                    return;
                }
                $location.path("/");
            });
        };
    }]);

app.controller("RoomCtrl",['$scope',
    function($scope){

    }]);