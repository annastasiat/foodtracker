angular.module("password", [])
    .controller("PasswordChangeCtrl", ["$scope", "$http", function ($scope, $http) {
        console.log("PasswordChangeCtrl");

        $scope.passwordDto = {};
        $scope.showIncorrectPassword = false;


        $scope.changePassword = function (passwordDto) {
            $http({
                method: "POST",
                url: "/api/user/change_password",
                data: $.param(passwordDto),
                headers: {"Content-Type" : "application/x-www-form-urlencoded" }
            }).then(
                function (data) {
                    $scope.showIncorrectPassword = false;
                },
                function (error) {
                    $scope.showIncorrectPassword = true;
                }
            );
        }

    }]);