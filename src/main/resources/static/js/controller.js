'use strict';

angular.module("app")
.controller('titleController', function ($scope) {
    $scope.$on('$routeChangeSuccess', function (event, current, previous) {
        $scope.title = current.title;
    });
})
.controller('homeController', function ($scope) {
    var vm = this;

    $scope.$on('$routeChangeSuccess', function (event, current, previous) {
        if(current.showMenu) {
            $scope.showMenu = current.showMenu;
        } else {
            $scope.showMenu = false;
        }
    });
})
.controller('errorController', function ($scope) {
    $scope.$on('$routeChangeSuccess', function (event, current, previous) {
        $("#alert-wrong-address").show();
        setTimeout(function() { $("#alert-wrong-address").hide(); }, 3000);
    });
})
.controller('authenticationController', function (User, UserService) {
    var vm = this;
    vm.user = new User();

    this.login = function () {
        UserService.getUser(vm.user);
        vm.user = new User();
    }
})
.controller('registrationController', function (User, UserService) {
    var vm = this;
    vm.user = new User();

    vm.registerUser = function () {
        UserService.registerUser(vm.user);
        vm.user = new User();
    }
})
.controller('projectController', function (ProjectService, Project, $scope, $location) {
    var vm = this;
    vm.project = new Project();
    vm.projectList = new Project();
    loadData();
    $scope.disabled = "disabled"; // Dezaktywowanie linków menu

    function loadData() {
        vm.projectList = ProjectService.getAll();
    }
    vm.openProject = function (projectId) {
        $location.path("/projectDetails/"+projectId);
    };
    vm.saveProject = function () {
        if(vm.project.name !== undefined && vm.project.description !== undefined && vm.project.projectAdmin !== undefined) {
            ProjectService.save(vm.project);
            vm.project = new Project(); // Do wyczyszczenia pól w formularzu
            setTimeout(function() { loadData(); }, 500);
        } else {
            console.log("Wypełnij wszytkie pola!");
        }
    };
    vm.updateProject = function (project) {
        ProjectService.update(project);
        vm.project = new Project(); // Do wyczyszczenia pól w formularzu
        setTimeout(function() { loadData(); }, 500);
    };
    vm.removeProject = function (projectId) {
        ProjectService.remove(projectId);
        setTimeout(function() { loadData(); }, 500);
    };
})
.controller('projectDetails', function ($scope, $routeParams, $location, Project, ProjectService, Sprint, SprintService) {
    var vm = this;
    vm.sprint = new Sprint();
    vm.project = new Project();
    vm.project = ProjectService.get($routeParams.projectId);
    vm.sprintList = [];
    loadProjectDetails();

    function loadProjectDetails() {
        vm.sprintList = SprintService.getSprintsInProject($routeParams.projectId);
    }
    vm.isSprintListEmpty = function () {
        return vm.sprintList.length === 0;
    }
    vm.openSprint = function (sprintId) {
        $location.path("/sprintDetails/"+sprintId);
    };
    vm.createSprint = function () {
        vm.sprint.project = vm.project;
        SprintService.save(vm.sprint);
        vm.sprint = new Sprint();
        $("#createSprintModal").modal('hide');
        setTimeout(function() { loadProjectDetails(); }, 500);
    }
    vm.removeSprint = function (sprintId) {
        SprintService.remove(sprintId);
        setTimeout(function() { loadProjectDetails(); }, 500);
    }
})
.controller('sprintController', function (Sprint, SprintService) {
    var vm = this;
    vm.sprint = new Sprint();
    vm.sprintList = SprintService.getSprints();

    vm.isEmpty = function () {
        return vm.sprintList.length === 0;
    }
})
.controller('sprintDetails', function ($scope, $routeParams, Sprint, SprintService, Task, TaskService, StoryPoint, StoryPointService) {
    var vm = this;
    vm.task = new Task();
    vm.storyPoint = new StoryPoint();
    vm.sprint = new Sprint();
    vm.sprint = SprintService.getSprintById($routeParams.sprintId);
    vm.taskList = [];
    vm.storyPointList = [];
    loadSprintDetails();

    function loadSprintDetails() {
        //vm.taskList = TaskService.getTasksInSprint($routeParams.sprintId);
        console.table(vm.sprint);
        console.log("id route params: "+$routeParams.sprintId);
    }
    function loadStoryPointsForTask(taskId) {
        vm.storyPointList = StoryPointService.getStoryPointsInTask(taskId);
    }
    vm.isTaskListEmpty = function () {
        return vm.taskList.length === 0;
    }
    vm.isStoryPointListEmpty = function () {
        return vm.storyPointList.length === 0;
    }
    vm.createTask = function () {
        vm.task.sprint = vm.sprint;
        TaskService.saveTask(vm.task);
        vm.task = new Task();
        $("#createTaskModal").modal('hide');
        setTimeout(function() { loadSprintDetails(); }, 500);
    }
    vm.removeTask = function (taskId) {
        TaskService.removeTask(taskId);
        setTimeout(function() { loadSprintDetails(); }, 500);
    }
    vm.createStoryPoint = function () {

    }
    vm.removeStoryPoint= function (storyPointId) {

    }
})
.controller('taskController', function (Task, TaskService, StoryPoint, StoryPointService) {
    var vm = this;
    vm.task = new Task();
    vm.storyPoint = new StoryPoint();
    vm.taskList = [];
    vm.storyPointList = [];
    vm.taskList = TaskService.getTasks();
    vm.storyPointList = StoryPointService.getStoryPoints();

    vm.isTaskListEmpty = function () {
        return vm.taskList.length === 0;
    }
    vm.isStoryPointListEmpty = function () {
        return vm.storyPointList.length === 0;
    }
})
.controller('participantController', function () {
});

