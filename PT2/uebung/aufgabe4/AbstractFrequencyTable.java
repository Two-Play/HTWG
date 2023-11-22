package aufgabe4;

import aufgabe4.FrequencyTable;

/**
 *
 * @author oliverbittel
 */
public abstract class AbstractFrequencyTable<T> implements FrequencyTable<T> {
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	@Override
    public void add(T w) {
		add(w, 1);
    }

	@Override
	public void addAll(FrequencyTable<? extends T> fq) {
		for (Element<? extends T> el : fq) {
			add(el.getElement(), el.getFrequency());
		}
	}

	@Override
	public void collectNMostFrequent(int n, FrequencyTable<? super T> fq) {
		fq.clear();

		if (this.size() <= n) {
			fq.addAll(this);

			return;
		}

		int i = 0;

		for (var e : this) {
			fq.add(e.getElement(), e.getFrequency());
			i++;
			if (i == n) {
				break;
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("{");

		for (Element<T> el : this) {
			s.append(el);
			s.append(", ");
		}

		s.append("} size = ");
		s.append(this.size());

		return s.toString();
	}
}
