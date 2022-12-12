
const GAME_OBJECTS = []

export default class  GameObject{
  constructor(){
    GAME_OBJECTS.push(this)
    this.timedelta = 0
    this.hasStarted = false
  }

  start(){
  }

  update(){
  }

  onDestory(){
  }

  destroy(){
    this.onDestory()
    for(let i in GAME_OBJECTS){
      if(GAME_OBJECTS[i] == this){
        GAME_OBJECTS.splice(i, 1)
        break
      }
    }
  }
  
}

let last_timestamp = 0

const step = (timestamp) => {
  for(let obj of GAME_OBJECTS){
    if(!obj.hasStarted){
      obj.start()
      obj.hasStarted = true
    }
    obj.timedelta = timestamp - last_timestamp
    obj.update()
  }
  last_timestamp = timestamp
  requestAnimationFrame(step)
}

requestAnimationFrame(step)