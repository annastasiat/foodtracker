angular.module("admin", [])
    .controller("UserCtrl", ["$scope", "$http", function ($scope, $http) {
        console.log("userCtrl")
        $scope.users = [];
        $scope.test="test123";
        $scope.foods = [];
        $scope.usersFood = [];
        $scope.getUsers = function(){
            $http({
                method: "GET",
                url: "/api/admin/all_users",
                headers: { "Content-Type" : "application/json" }
            }).then(
                function(data) {
                    $scope.users = data.data.users;
                },
                function(error) {
                    console.log("users error")
                }
            );
        }
        $scope.getFood = function(){
            $http({
                method: "GET",
                url: "/api/admin/all_food",
                headers: { "Content-Type" : "application/json" }
            }).then(
                function(data) {
                    $scope.foods = data.data.foods;
                },
                function(error) {
                    console.log("foods error")
                }
            );
        }
        $scope.getUsersFood = function(){
            $http({
                method: "GET",
                url: "/api/admin/all_users_food",
                headers: { "Content-Type" : "application/json" }
            }).then(
                function(data) {
                    $scope.usersFood = data.data.usersFood;
                },
                function(error) {
                    console.log("usersFood error")
                }
            );
        }

    }]);