import React, { useEffect, useState } from "react";
import './App.css';
import CodeBlock from './components/codeBlock/CodeBlock.jsx'

function App() {
    const [fullUrl, setFullUrl] = useState({ url: '' });
    const [tinyUrl, setTinyUrl] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        fetch('http://localhost:8080/api/tinyUrl/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body:JSON.stringify(fullUrl),
        })
        .then(response => response.json())
        .then(data => setTinyUrl(data.link))
        .catch(error => console.error('Error creating tinyurl:', error));
    };

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFullUrl(prevFullUrl => ({ ...prevFullUrl, [name]: value }));
    };

    return (
        <div className={"mainDiv"}  style={{display: 'grid', placeItems: 'center', margin: '200px 0 0'}}>
            <div className={"logo"}></div>
            <form onSubmit={handleSubmit}>
                <input type="text"
                style={{width: "400px", height:"24px"}}
                name="url"
                value={tinyUrl ? "" : fullUrl.url}
                onChange={handleChange}
                placeholder="Enter URL" />
                <button type="submit" disabled={fullUrl.url ? false : true}>Tinify</button>
            </form>

            {tinyUrl && (
                <CodeBlock tinyUrl={tinyUrl} />
            )}
        </div>
    );
 }

export default App;
