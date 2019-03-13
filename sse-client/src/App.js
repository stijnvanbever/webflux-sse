import React, {Component} from 'react';
import './App.css';

class App extends Component {
    state = {
        events: []
    };

    constructor(props) {
        super();

    }

    componentDidMount() {
        this.eventSource = new EventSource("http://localhost:8080/routerfunction/flux-sse");
        this.eventSource.onopen = msg => console.log("open:", msg);
        this.eventSource.onerror = msg => console.log("error:", msg);
        this.eventSource.onmessage = e => {
            console.log(e);
            this.updateEvents(e.data);
        }
    }

    updateEvents(newEvent) {
        console.log(newEvent);
        this.setState({
            events: [...this.state.events, newEvent]
        });
    }


    render() {
        return (
            <div>
                <ul>
                    {this.state.events.map((test, i) =>
                        <li>{test}</li>
                    )}
                </ul>
            </div>
        );
    }
}

export default App;
