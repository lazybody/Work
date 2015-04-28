/**
 * Created by wanghui21 on 2015/4/28.
 */
var services = angular.module('chatroom.services',['ngResource']);

services.factory('Account',['$resource',
    function($resource){
        return $resource('chatroom11/:id',{id:'@id'});
    }]);