angular.module('app', []);
angular.module('app').controller('MainCtrl', ['$scope', function ($scope) {

  $scope.input = 'Ping!';

  $scope.actorWs = null;
  $scope.iterateeWs = null;

  $scope.actorResults = [];
  $scope.iterateeResults = [];

  $scope.init = function () {
    $scope.actorWs = new WebSocket('ws://localhost:9000/actorWs');
    var actorWsHandler = function (event) {
      $scope.$apply(function () {
        $scope.actorResults.push(event.data);
      });
    };
    $scope.actorWs.onopen = actorWsHandler;
    $scope.actorWs.onmessage = actorWsHandler;
    $scope.actorWs.onclose = actorWsHandler;
    $scope.actorWs.onerror = actorWsHandler;

    $scope.iterateeWs = new WebSocket('ws://localhost:9000/iterateeWs');
    var iterateeWsHandler = function (event) {
      $scope.$apply(function () {
        $scope.iterateeResults.push(event.data);
      });
    };
    $scope.iterateeWs.onopen = iterateeWsHandler;
    $scope.iterateeWs.onmessage = iterateeWsHandler;
    $scope.iterateeWs.onclose = iterateeWsHandler;
    $scope.iterateeWs.onerror = iterateeWsHandler;
  };

  $scope.send = function () {
    $scope.actorWs.send($scope.input);
    $scope.iterateeWs.send($scope.input);
  };

  $scope.clear = function () {
    $scope.actorResults = [];
    $scope.iterateeResults = [];
  };

}]);
