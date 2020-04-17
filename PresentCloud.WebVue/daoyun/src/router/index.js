import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
import AdminHome from '@/components/Admin/AdminHome'
import Userlist from '@/components/Admin/Userlist/Userlist'
import Classes from '@/components/Admin/Classes'
import Class from '@/components/Admin/Class'
import SchoolMate from '@/components/Admin/SchoolMate'
import Sign from '@/components/Admin/Sign'
import Task from '@/components/Admin/Task'
import Detail from '@/components/Admin/Detail'
import TaskDetails from '@/components/Admin/TaskDetails'

import TeacherHome from '@/components/Teacher/TeacherHome'
import TeacherClasses from '@/components/Teacher/Classes'
import TeacherClass from '@/components/Teacher/Class'
import TeacherSchoolMate from '@/components/Teacher/SchoolMate'
import TeacherSign from '@/components/Teacher/Sign'
import TeacherTask from '@/components/Teacher/Task'
import TeacherDetail from '@/components/Teacher/Detail'
import TeacherTaskDetails from '@/components/Teacher/TaskDetails'

import StudentHome from '@/components/Student/StudentHome'
import StudentClasses from '@/components/Student/Classes'
import StudentClass from '@/components/Student/Class'
import StudentSchoolMate from '@/components/Student/SchoolMate'
import StudentSign from '@/components/Student/Sign'
import StudentTask from '@/components/Student/Task'
import StudentDetail from '@/components/Student/Detail'
import StudentTaskDetails from '@/components/Student/TaskDetails'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/adminhome',
      name: 'AdminHome',
      component: AdminHome,
      children: [
        {
          path: '/users',
          name: 'Userlist',
          component: Userlist
        },
        {
          path: '/classes',
          name: 'Classes',
          component: Classes
        },
        {
          path: '/class',
          name: 'Class',
          component: Class,
          children: [
            {
              path: '/schoolmate',
              name: 'SchoolMate',
              component: SchoolMate
            },
            {
              path: '/sign',
              name: 'Sign',
              component: Sign
            },
            {
              path: '/task',
              name: 'Task',
              component: Task
            },
            {
              path: '/detail',
              name: 'Detail',
              component: Detail
            },
            {
              path: '/taskdetails',
              name: 'TaskDetails',
              component: TaskDetails
            },
          ]
        },
      ]
    },
    {
      path: '/teacherhome',
      name: 'TeacherHome',
      component: TeacherHome,
      children: [
        {
          path: '/teacher/classes',
          name: 'TeacherClasses',
          component: TeacherClasses
        },
        {
          path: '/teacher/class',
          name: 'TeacherClass',
          component: TeacherClass,
          children: [
            {
              path: '/teacher/schoolmate',
              name: 'TeacherSchoolMate',
              component: TeacherSchoolMate
            },
            {
              path: '/teacher/sign',
              name: 'TeacherSign',
              component: TeacherSign
            },
            {
              path: '/teacher/task',
              name: 'TeacherTask',
              component: TeacherTask
            },
            {
              path: '/teacher/detail',
              name: 'TeacherDetail',
              component: TeacherDetail
            },
            {
              path: '/teacher/taskdetails',
              name: 'TeacherTaskDetails',
              component: TeacherTaskDetails
            },
          ]
        },
      ]
    },
    {
      path: '/studenthome',
      name: 'StudentHome',
      component: StudentHome,
      children: [
        {
          path: '/student/classes',
          name: 'StudentClasses',
          component: StudentClasses
        },
        {
          path: '/student/class',
          name: 'StudentClass',
          component: StudentClass,
          children: [
            {
              path: '/student/schoolmate',
              name: 'StudentSchoolMate',
              component: StudentSchoolMate
            },
            {
              path: '/student/sign',
              name: 'StudentSign',
              component: StudentSign
            },
            {
              path: '/student/task',
              name: 'StudentTask',
              component: StudentTask
            },
            {
              path: '/student/detail',
              name: 'StudentDetail',
              component: StudentDetail
            },
            {
              path: '/student/taskdetails',
              name: 'StudentTaskDetails',
              component: StudentTaskDetails
            },
          ]
        },
      ]
    },
  ]
})
