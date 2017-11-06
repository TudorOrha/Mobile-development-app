import React from 'react';

export default class Issue {
    name: string;
    sprint: string;

    constructor(name:string,sprint:string) {
        this.name = name;
        this.sprint = sprint;
    }

    getName(){
        return this.name;
    }

    getSprint(){
        return this.sprint;
    }

    setName(name:string){
        this.name = name;
    }

    setSprint(sprint:string){
        this.sprint = sprint;
    }
}