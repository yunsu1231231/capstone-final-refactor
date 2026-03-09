import { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { getRequestsByTrainer, updateRequestStatus } from '../api/coachingRequests';

export default function TrainerFlow() {
  const { trainerId } = useParams();
  const [requests, setRequests] = useState([]);

  const load = () => getRequestsByTrainer(trainerId).then(setRequests).catch(() => setRequests([]));

  useEffect(() => {
    load();
  }, [trainerId]);

  return (
    <div className="page">
      <header className="header">
        <Link to="/">← 홈</Link>
        <span>트레이너 (ID: {trainerId})</span>
      </header>

      <section className="card">
        <h2>2. 요청 목록 (받은 요청)</h2>
        {!requests?.length ? (
          <p className="muted">받은 요청이 없습니다.</p>
        ) : (
          <ul className="list">
            {requests.map((r) => (
              <li key={r.id} className="list-item">
                <span>회원: {r.memberName} (ID {r.memberId})</span>
                <span className="badge">{r.status === 'REQUESTED' ? '대기' : r.status === 'ACCEPTED' ? '수락됨' : '거절됨'}</span>
                {r.status === 'REQUESTED' && (
                  <TrainerActions requestId={r.id} onDone={load} updateStatus={updateRequestStatus} />
                )}
                {r.chatRoomId && (
                  <Link to={`/chat/${r.chatRoomId}?memberId=${r.trainerId}&name=트레이너`} className="btn-sm">
                    채팅하기
                  </Link>
                )}
              </li>
            ))}
          </ul>
        )}
      </section>
    </div>
  );
}

function TrainerActions({ requestId, onDone, updateStatus }) {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handle = async (status) => {
    setError('');
    setLoading(true);
    try {
      await updateStatus(requestId, status);
      onDone();
    } catch (err) {
      setError(err.message || '처리 실패');
    } finally {
      setLoading(false);
    }
  };

  return (
    <span className="row">
      <button type="button" className="btn-sm primary" disabled={loading} onClick={() => handle('ACCEPTED')}>
        수락
      </button>
      <button type="button" className="btn-sm" disabled={loading} onClick={() => handle('REJECTED')}>
        거절
      </button>
      {error && <span className="error">{error}</span>}
    </span>
  );
}
