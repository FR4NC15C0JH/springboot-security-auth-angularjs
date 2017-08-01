(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];
    function UserService($http) {
        var service = {};

//        service.GetAll = GetAll;
//        service.GetById = GetById;
        service.GetByEmail = GetByEmail;
        service.createNewUser = createNewUser;
//        service.Update = Update;
//        service.Delete = Delete;

        return service;

//        function GetAll() {
//            return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
//        }
//
//        function GetById(id) {
//            return $http.get('/api/users/' + id).then(handleSuccess, handleError('Error getting user by id'));
//        }

        function GetByEmail(email) {
            return $http.get('/user/getUserEmail' + email).then(handleSuccess, handleError('Error getting user by email'));
        }

        function createNewUser(user) {
            return $http.post('/api/register', user).then(handleSuccess, handleError('Error creating user'));
        }

//        function Update(user) {
//            return $http.put('/api/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
//        }
//
//        function Delete(id) {
//            return $http.delete('/api/users/' + id).then(handleSuccess, handleError('Error deleting user'));
//        }

        // private functions

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();