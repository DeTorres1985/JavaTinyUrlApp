import React, { useState } from 'react';
import './CodeBlock.css';

const CodeBlock = ({ tinyUrl }) => {
    const [code, setCode] = useState(tinyUrl);
    const [copied, setCopied] = useState(false);

    const copyCode = () => {
        navigator.clipboard.writeText(tinyUrl);
        setCopied(true);
        setTimeout(() => setCopied(false), 2000); // Сброс состояния через 2 секунды
    };

    return (
        <div className={"code-block"}>
            <pre>
                {tinyUrl}
            </pre>
            <button className={"copy"} onClick={copyCode} disabled={copied}>
                {copied ? 'Copied!' : 'Copy'}
            </button>
        </div>
    );
};

export default CodeBlock;