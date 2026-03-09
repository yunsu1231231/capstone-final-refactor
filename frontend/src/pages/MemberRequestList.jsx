import { useOutletContext } from 'react-router-dom';
import { Link } from 'react-router-dom';

const STATUS = { REQUESTED: '대기', ACCEPTED: '수락됨', REJECTED: '거절됨' };

export default function MemberRequestList() {
  const { requests } = useOutletContext();

  return (
    <section className="card">
      <h2>2. 요청 목록 (내가 보낸 요청)</h2>
      {!requests?.length ? (
        <p className="muted">보낸 요청이 없습니다.</p>
      ) : (
        <ul className="list">
          {requests.map((r) => (
            <li key={r.id} className="list-item">
              <span>트레이너: {r.trainerName} (ID {r.trainerId})</span>
              <span className="badge">{STATUS[r.status] ?? r.status}</span>
              {r.chatRoomId && (
                <Link to={`/chat/${r.chatRoomId}?memberId=${r.memberId}&name=${encodeURIComponent(r.memberName)}`} className="btn-sm">
                  채팅하기
                </Link>
              )}
            </li>
          ))}
        </ul>
      )}
    </section>
  );
}
