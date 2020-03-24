angular.module("food", [])
    .controller("FoodCtrl", ["$scope", "$http", function ($scope, $http) {
        console.log("foodCtrl");

        $scope.userFood = {};
        $scope.food = {};
        $scope.showAddUserSuccess = false;
        $scope.showAddSuccess = false;
        $scope.showAddUserError = false;
        $scope.showAddError = false;


        $scope.addUSerFood = function (userFood) {
            $http({
                method: "POST",
                url: "/api/user/add_user_food",
                data: $.param(userFood),
                headers: {"Content-Type" : "application/x-www-form-urlencoded" }
            }).then(
                function (data) {
                    $scope.showAddUserSuccess = true;
                    $scope.showAddSuccess = false;
                    $scope.showAddUserError = false;
                    $scope.showAddError = false;
                },
                function (error) {
                    $scope.showAddUserError = true;
                    $scope.showAddUserSuccess = false;
                    $scope.showAddSuccess = false;
                    $scope.showAddError = false;
                }
            );
        }
        $scope.addFood = function (food) {
            $http({
                method: "POST",
                url: "/api/user/add_food",
                data: $.param(food),
                headers: {"Content-Type" : "application/x-www-form-urlencoded"}
            }).then(
                function (data) {
                    $scope.showAddSuccess = true;
                    $scope.showAddUserSuccess = false;
                    $scope.showAddUserError = false;
                    $scope.showAddError = false;

                },
                function (error) {
                    $scope.showAddError = true;
                    $scope.showAddUserSuccess = false;
                    $scope.showAddSuccess = false;
                    $scope.showAddUserError = false;

                }
            );
        }

    }]);