'use strict';

angular.module("app")
.constant('USER_ENDPOINT', '/api/user/:userName')
.constant('PROJECT_ENDPOINT', '/api/project/:id')
.constant('SPRINT_ENDPOINT', '/api/sprint/:id')
.constant('SPRINT_IN_PROJECT_ENDPOINT', '/api/sprint/project/:projectId')
.constant('TASK_ENDPOINT', '/api/task/:id')
.constant('TASK_IN_SPRINT_ENDPOINT', '/api/task/sprint/:sprintId')
.constant('STORY_POINT_ENDPOINT', '/api/sp/:id')
.constant('STORY_POINT_IN_TASK_ENDPOINT', '/api/sp/task/:taskId')
.factory('User', function ($resource, USER_ENDPOINT) {
    return $resource(USER_ENDPOINT);
})
.factory('Project', function ($resource, PROJECT_ENDPOINT) {
    return $resource(PROJECT_ENDPOINT, {}, {'update': {method: 'PUT', params: 'id'}});
})
.factory('Sprint', function ($resource, SPRINT_ENDPOINT) {
    return $resource(SPRINT_ENDPOINT);
})
.factory('SprintInProject', function ($resource, SPRINT_IN_PROJECT_ENDPOINT) {
    return $resource(SPRINT_IN_PROJECT_ENDPOINT);
})
.factory('Task', function ($resource, TASK_ENDPOINT) {
    return $resource(TASK_ENDPOINT);
})
.factory('TaskInSprint', function ($resource, TASK_IN_SPRINT_ENDPOINT) {
    return $resource(TASK_IN_SPRINT_ENDPOINT);
})
.factory('StoryPoint', function ($resource, STORY_POINT_ENDPOINT) {
    return $resource(STORY_POINT_ENDPOINT);
})
.factory('StoryPointInTask', function ($resource, STORY_POINT_IN_TASK_ENDPOINT) {
    return $resource(STORY_POINT_IN_TASK_ENDPOINT);
})
.factory('Participant', function ($resource, PARTICIPANT_ENDPOINT) {
    return $resource(PARTICIPANT_ENDPOINT);
})
.service('UserService', function (User) {
    this.getUser = function (user) {
        return User.get({userName: user.login}, function success(user) {
            console.log("Użytkownik pobrany poprawnie!");
            console.log(user);
        }, function error() {
            console.log("Błąd - użytkownik nie odnaleziony!");
        });
    }
    this.registerUser = function (user) {
        user.$save(function success() {
            console.log("Użytkownik dodany poprawnie!");
        }, function error() {
            console.log("Błąd dodawania użytkownika!");
        });
    }
})
.service('ProjectService', function (Project) {
    this.getAll = function () {
        return Project.query(
            function success(data, headers) {
                console.log('Pobrano dane: ' +  data);
                console.log(headers('Content-Type'));
            },
            function error(response) {
                console.log('Status: ' + response.status); //np. 404
                console.log('Data: '+ response.data);
            }
        );
    }
    this.get = function(projectId) {
        return Project.get({id: projectId});
    }
    this.save = function (project) {
        project.$save(function success() {
            console.log("Projekt utworzony poprawnie.");
        }, function error() {
            console.log("Błąd podczas tworzenia projektu!");
        });
    }
    this.update = function (project) {
        project.$update({id: project.id}, function success() {
            console.log("Projekt zaktualizowano!");
        },
        function error(response) {
            console.log("Błąd - edycja nieudana!");
            console.log('Status: ' + response.status);
            console.log('Data: '+ response.data);
        });
    }
    this.remove = function (projectId) {
        Project.remove({id: projectId}, function success() {
            console.log("Projekt usunięty!");
        },
        function error(response) {
            console.log("Nie znaleziono projektu!");
            console.log('Status: ' + response.status);
            console.log('Data: '+ response.data);
        });
    }
})
.service('SprintService', function (Sprint, SprintInProject) {
    this.getSprints = function () {
        return Sprint.query();
    }
    this.getSprintById = function (sprintId) {
        return Sprint.get({id: sprintId});
    }
    this.getSprintsInProject = function (projectId) {
        return SprintInProject.query({projectId: projectId});
    }
    this.save = function (sprint) {
        sprint.$save(function success() {
            console.log("Nowy sprint utworzony pomyślnie!");
        }, function error() {
            console.log("Błąd podczas tworzenia sprintu!");
            console.log(sprint);
        });
    }
    this.remove = function (sprintId) {
        Sprint.remove({id: sprintId}, function success() {
                console.log("Sprint id: "+sprintId+" usunięto pomyślnie!");
            },
            function error(response) {
                console.log("Nie znaleziono sprintu!");
                console.log('Status: ' + response.status);
                console.log('Data: '+ response.data);
            });
    }
})
.service('TaskService', function (Task, TaskInSprint) {
    this.getTasks = function () {
        return Task.query();
    }
    this.getTaskById = function (taskId) {
        return Task.get({id: taskId});
    }
    this.getTasksInSprint = function (sprintId) {
        return TaskInSprint.query({sprintId: sprintId});
    }
    this.saveTask = function (task) {
        task.$save(function success() {
            console.log("Nowe zadanie utworzone pomyślnie!");
        }, function error() {
            console.log("Błąd podczas tworzenia zadania!");
            console.log(task);
        });
    }
    this.removeTask = function (taskId) {
        Task.remove({id: taskId}, function success() {
                console.log("Zadanie id: "+taskId+" usunięto pomyślnie!");
            },
            function error(response) {
                console.log("Nie znaleziono zadania!");
                console.log('Status: ' + response.status);
                console.log('Data: '+ response.data);
            });
    }
})
.service('StoryPointService', function (StoryPoint, StoryPointInTask) {
    this.getStoryPoints = function () {
        return StoryPoint.query();
    }
    this.getStoryPointById = function (storyPointId) {
        return StoryPoint.get({id: storyPointId});
    }
    this.getStoryPointsInTask = function (taskId) {
        return StoryPointInTask.query({taskId: taskId});
    }
    this.saveStoryPoint = function (storyPoint) {
        storyPoint.$save(function success() {
            console.log("Nowy story point utworzony pomyślnie!");
        }, function error() {
            console.log("Błąd podczas tworzenia story point!");
            console.log(storyPoint);
        });
    }
    this.removeStoryPoint = function (storyPointId) {
        StoryPoint.remove({id: storyPointId}, function success() {
                console.log("Story point id: "+storyPointId+" usunięto pomyślnie!");
            },
            function error(response) {
                console.log("Nie znaleziono story point!");
                console.log('Status: ' + response.status);
                console.log('Data: '+ response.data);
            });
    }
});