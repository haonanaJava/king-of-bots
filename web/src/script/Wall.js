import GameObject from "./GameObject";

/**
 * 地图墙体对象
 */
export default class  Wall extends GameObject{
  constructor(r, c, gameMap){
    super()
    this.r = r
    this.c = c
    this.gameMap = gameMap
    this.color = '#B37266'
  }

  start(){

  }

  update(){
    this.render()
  }

  render(){
    const L = this.gameMap.L
    const ctx = this.gameMap.ctx

    ctx.fillStyle = this.color
    ctx.fillRect(this.c * L, this.r * L, L, L)

  }
}