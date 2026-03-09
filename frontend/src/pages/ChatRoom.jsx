import { useEffect, useState, useCallback } from 'react';
import { Link, useParams, useSearchParams } from 'react-router-dom';
import { getMessages } from '../api/chat';
import { useStompChat } from '../hooks/useStompChat';

export default function ChatRoom() {
  const { chatRoomId } = useParams();
  const [searchParams] = useSearchParams();
  const memberId = searchParams.get('memberId');
  const memberName = searchParams.get('name') || '사용자';

  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState('');
  const [loading, setLoading] = useState(true);

  const onMessage = useCallback((msg) => {
    setMessages((prev) => [...prev, { ...msg, id: Date.now() }]);
  }, []);

  const { connected, send } = useStompChat(chatRoomId, memberId, memberName, onMessage);

  useEffect(() => {
    getMessages(chatRoomId)
      .then(setMessages)
      .catch(() => setMessages([]))
      .finally(() => setLoading(false));
  }, [chatRoomId]);

  const handleSend = (e) => {
    e.preventDefault();
    const text = input.trim();
    if (!text) return;
    send(text);
    setInput('');
  };

  return (
    <div className="page chat-page">
      <header className="header">
        <Link to="/">← 홈</Link>
        <span>채팅방 {chatRoomId}</span>
        {connected ? <span className="status on">연결됨</span> : <span className="status">연결 중…</span>}
      </header>

      <section className="card chat-card">
        <h2>5. 채팅 메시지 전송 · 6. 채팅 메시지 조회</h2>

        {loading ? (
          <p className="muted">메시지 불러오는 중…</p>
        ) : (
          <ul className="message-list">
            {messages.map((m, i) => (
              <li key={m.id ?? i} className="message">
                <span className="message-sender">{m.senderName ?? m.username ?? '?'}</span>
                <span className="message-content">{m.content}</span>
              </li>
            ))}
          </ul>
        )}

        <form onSubmit={handleSend} className="chat-form">
          <input
            type="text"
            value={input}
            onChange={(e) => setInput(e.target.value)}
            placeholder="메시지 입력..."
            disabled={!connected}
          />
          <button type="submit" disabled={!connected || !input.trim()}>
            전송
          </button>
        </form>
      </section>
    </div>
  );
}
