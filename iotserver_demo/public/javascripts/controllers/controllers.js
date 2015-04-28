/**
 * Created by wanghui21 on 2015/4/22.
 */
var app = angular.module('iot',[]);

app.config(['$routeProvider',function($routeProvider){
    $routeProvider.
        when('/',{
            controller:'deviceCtrl',
            templateUrl:'/views/device.html'
        }).
        otherwise({redirectTo:'/'})
}]);

app.controller('deviceCtrl',['$scope'/*, '$cordovaDeviceMotion'*/,function($scope/*,$cordovaDeviceMotion*/){

    /*$scope.watchAcceleration = function () {
        var options = { frequency: 1000 };  // Update every 1 second

        watch = $cordovaDeviceMotion.watchAcceleration(options);

        watch.promise.then(
            function() {},
            function(err) {},
            function(acceleration) {
                $scope.deviceX = acceleration.x;
            });
    };*/

}]);