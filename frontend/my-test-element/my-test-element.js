/**
 * 
 */
 /**
import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
class MyTestElement extends PolymerElement {
  static get template() {
    return html`
      <button id="helloButton" on-click="sayHello">Say hello</button>
      <div >Hello {{prop1}}</div>
      <div id="content"></div>
      <vaadin-text-field>
		  #shadow-root
		    <style>
		      [part="input-field"] {
		      	background: blue;
		      }
		    </style>
		</vaadin-text-field>	
		my test end	    
    `;
  }
}
**/
import {LitElement, html, css} from 'lit-element';

export class MyTestElement extends LitElement {

  static get properties() {
    return {
      prop1: { type: String }
    };
  }

  static get styles() {
    return css`
        :host {
          display: block;
        }

        .my-view-title {
          font-weight: bold;
          border-bottom: 1px solid gray;
        }
    `;
  }
   
    render() {
        return html`
      <button id="helloButton" class="my-view-title">Say hello ${this.prop1}</button>
      <div id="content"></div>
      <vaadin-text-field id="comments">
		  #shadow-root
		    <style>
		      [part="input-field"] {
		      	background: blue;
		      }
		    </style>
		</vaadin-text-field>	
		<button id="commentsButton"  @click="${this.commentsClick}">comments</button>
		<div style="border: 1px solid black; padding: 10px; margin: 10px;">
		    <slot>No components added</slot>
		</div>

		`;
    }
    commentsClick(e) {
    	//alert(e.target);
       let greetingPromise = this.$server.getGreeting("JavaScript");
  	   greetingPromise.then(greeting => console.log(greeting));

    }
    clientmethod() {
     return this.prop1;
    }
}


window.customElements.define('my-test-element', MyTestElement);
