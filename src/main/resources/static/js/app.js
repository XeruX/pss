'use strict';

angular.module("app", ['ngRoute', 'ngResource'])
.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            title: 'Home',
            templateUrl: "templates/home.html",
            controller: 'homeController',
            controllerAs: 'homeCtrl'
        })
        .when('/login', {
            title: 'Zaloguj się',
            templateUrl: 'templates/login.html',
            controller: 'authenticationController',
            controllerAs: 'authCtrl'
        })
        .when('/rejestracja', {
            title: 'Tworzenie konta',
            templateUrl: 'templates/rejestracja.html',
            controller: 'registrationController',
            controllerAs: 'registrationCtrl'
        })
        .when('/projekty', {
            title: 'Projekty',
            templateUrl: 'templates/projekty.html',
            controller: 'projectController',
            controllerAs: 'projectCtrl',
            showMenu: true
        })
        .when('/projectDetails/:projectId', {
            title: 'Szczegóły projektu',
            templateUrl: 'templates/projectDetails.html',
            controller: 'projectDetails',
            controllerAs: 'projectDet',
            showMenu: true
        })
        .when('/sprinty', {
            title: 'Sprinty',
            templateUrl: 'templates/sprinty.html',
            controller: 'sprintController',
            controllerAs: 'sprintCtrl',
            showMenu: true
        })
        .when('/sprintDetails/:sprintId', {
            title: 'Szczegóły sprintu',
            templateUrl: 'templates/sprintDetails.html',
            controller: 'sprintDetails',
            controllerAs: 'sprintDet',
            showMenu: true
        })
        .when('/zadania', {
            title: 'Zadania',
            templateUrl: 'templates/zadania.html',
            controller: 'taskController',
            controllerAs: 'taskCtrl',
            showMenu: true
        })
        .when('/uczestnicy', {
            title: 'Uczestnicy projektu',
            templateUrl: 'templates/uczestnicy.html',
            controller: 'participantController',
            controllerAs: 'participantCtrl',
            showMenu: true
        })
        .when('/error', {
            title: 'Błędny adres',
            templateUrl: 'templates/error.html',
            controller: 'errorController',
            controllerAs: 'errorCtrl',
            showMenu: true
        })
        .otherwise({
            redirectTo: '/error'
        });
});