'use strict';

angular.module('main', ['ngAnimate','ngMessages','ngRoute','ngCookies','App.Common','App.Admin','App.Auth','App'])

	.config(['$routeProvider', function ($routeProvider){
        $routeProvider
        
            .when('/home', {
                controller: 'HomeController',
                templateUrl: 'templates/admin/home.html',
                controllerAs: 'home'
            })

            .when('/admin.login', {
                controller: 'LoginController',
                templateUrl: 'templates/login.html',
                controllerAs: 'lc'
            })

            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'templates/login.html',
                controllerAs: 'lc'
            })

            .when('/registration', {
                controller: 'RegisterController',
                templateUrl: 'templates/registration.html',
                controllerAs: 'rc'
            })

            .when('/access-denied', {
                controller: 'LoginController',
                templateUrl: 'templates/access-denied.html',
                controllerAs: 'lc'
            })

            .when('/admin', {
                controller: 'AdminController',
                templateUrl: 'templates/admin/home.html',
                controllerAs: 'adm'
            })

            .when('/app', {
                controller: 'AppController',
                templateUrl: 'templates/admin/home.html',
                controllerAs: 'app'
            })

            .otherwise({ redirectTo: '/home' });
    }
])

	.run(['$rootScope', '$location', '$cookieStore', '$http',
    function ($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Bearer ' + $rootScope.globals.token;
            $rootScope.currentUser = $rootScope.globals.currentUser;
        }

        $rootScope.isSubmitted = false;

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            console.log('received event: ' + event + ' from: ' + current + ' to go to next: ' + next);
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login', '/registration', '/admin.login', '/adm.register', '/admin', '/app', '/dashboard']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            $rootScope.currentUser = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
                if($location.path().indexOf('admin') > -1) {
                    $location.path('/admin.login');
                } else if($location.path().indexOf('app') > -1) {
                    $location.path('/login');
                } else {
                    $location.path('/home');
                }
            }
        });
    }
]);