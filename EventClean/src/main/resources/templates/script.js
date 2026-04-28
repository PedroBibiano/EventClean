// ==========================================
// VARIÁVEIS GLOBAIS
// ==========================================
let eventos = [];
const API_URL = 'http://localhost:8080/api/v1'; // Ajuste se o seu mapeamento base no Java for diferente

// ==========================================
// COMUNICAÇÃO COM O BACK-END (JAVA)
// ==========================================

// Buscar eventos do Java
function carregarEventosDoServidor() {
    fetch(`${API_URL}/listareventos`) // Ajuste a rota se necessário
        .then(response => response.json())
        .then(dados => {
            eventos = dados;

            const countBadge = document.getElementById('event-count');
            if (eventos.length > 0) {
                countBadge.textContent = eventos.length;
                countBadge.classList.remove('hidden');
            } else {
                countBadge.classList.add('hidden');
            }

            // Chama a função para desenhar os eventos no ecrã
            renderEvents();
        })
        .catch(error => console.error('Erro ao buscar do Java:', error));
}

// Salvar um novo evento
document.addEventListener('DOMContentLoaded', () => {
    const eventoForm = document.getElementById('eventoForm');

    if (eventoForm) {
        eventoForm.addEventListener('submit', function(event) {
            event.preventDefault(); // Impede o "piscar" e recarregamento da página

            const novoEvento = {
                nome: document.getElementById('nome').value,
                descricao: document.getElementById('descricao').value,
                dataInicio: document.getElementById('dataInicio').value,
                dataFim: document.getElementById('dataFim').value,
                identificador: document.getElementById('identificador').value,
                organizador: document.getElementById('organizador').value,
                capacidade: parseInt(document.getElementById('capacidade').value),
                tipo: document.getElementById('tipo').value,
                localEvento: document.getElementById('localEvento').value
            };

            fetch(`${API_URL}/criarevento`, { // Ajuste a rota se necessário (ex: /salvar)
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(novoEvento)
            })
                .then(response => {
                    if (response.ok) {
                        showToast('Evento salvo com sucesso!');
                        eventoForm.reset();
                    } else {
                        throw new Error('Erro ao salvar o evento');
                    }
                })
                .catch(error => {
                    console.error('Erro na submissão:', error);
                    alert('Erro ao salvar o evento. Verifique a consola.');
                });
        });
    }
});

// ==========================================
// FUNÇÕES DE INTERFACE (UI)
// ==========================================

// Alternar entre abas
function changeTab(tabName) {
    const tabCreate = document.getElementById('tab-create');
    const tabList = document.getElementById('tab-list');
    const contentCreate = document.getElementById('content-create');
    const contentList = document.getElementById('content-list');

    const activeClasses = 'border-indigo-500 text-indigo-600'.split(' ');
    const inactiveClasses = 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'.split(' ');

    if (tabName === 'create') {
        tabCreate.classList.remove(...inactiveClasses);
        tabCreate.classList.add(...activeClasses);
        tabList.classList.remove(...activeClasses);
        tabList.classList.add(...inactiveClasses);

        contentCreate.classList.add('active');
        contentList.classList.remove('active');
    } else {
        tabList.classList.remove(...inactiveClasses);
        tabList.classList.add(...activeClasses);
        tabCreate.classList.remove(...activeClasses);
        tabCreate.classList.add(...inactiveClasses);

        contentList.classList.add('active');
        contentCreate.classList.remove('active');

        // Atualiza a lista sempre que entramos na aba
        carregarEventosDoServidor();
    }
}

// Desenhar a lista de eventos no ecrã (A função que estava em falta!)
function renderEvents() {
    const eventsGrid = document.getElementById('events-grid');
    const emptyState = document.getElementById('empty-state');

    eventsGrid.innerHTML = '';

    if (eventos.length === 0) {
        emptyState.classList.remove('hidden');
        eventsGrid.classList.add('hidden');
        return;
    }

    emptyState.classList.add('hidden');
    eventsGrid.classList.remove('hidden');

    eventos.forEach(evento => {
        const card = document.createElement('div');
        // Adicionada a classe 'event-card' para o CSS funcionar
        card.className = 'event-card bg-white overflow-hidden shadow-sm rounded-lg border border-gray-200 hover:shadow-md transition-shadow';

        card.innerHTML = `
            <button onclick="deletarEvento(${evento.id})" class="delete-btn absolute top-2 right-2 text-gray-400 p-1 z-10" title="Excluir evento">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                </svg>
            </button>

            <div class="px-4 py-5 sm:px-6 border-b border-gray-100 bg-gray-50 flex justify-between items-start">
                <div>
                    <h3 class="text-lg leading-6 font-medium text-gray-900">${evento.nome}</h3>
                    <p class="mt-1 max-w-2xl text-sm text-gray-500">${evento.tipo}</p>
                </div>
                <span class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-indigo-100 text-indigo-800">
                    ${evento.identificador}
                </span>
            </div>
            <div class="px-4 py-5 sm:p-6">
                <p class="text-sm text-gray-700 mb-4">${evento.descricao}</p>
                <div class="space-y-2 text-sm text-gray-600">
                    <div class="flex items-center gap-2">
                        <svg class="h-4 w-4 text-gray-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
                        </svg>
                        ${formatarData(evento.dataInicio)} - ${formatarData(evento.dataFim)}
                    </div>
                    <div class="flex items-center gap-2">
                        <svg class="h-4 w-4 text-gray-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                        </svg>
                        ${evento.localEvento}
                    </div>
                </div>
            </div>
        `;
        eventsGrid.appendChild(card);
    });
}

// Mostrar o alerta de sucesso
function showToast(message) {
    const toast = document.getElementById('toast');
    document.getElementById('toast-message').textContent = message;
    toast.classList.add('show');
    setTimeout(() => {
        toast.classList.remove('show');
    }, 3000);
}

// Formatar a data para padrão europeu/português
function formatarData(dataString) {
    if (!dataString) return '';
    const data = new Date(dataString);
    return data.toLocaleString('pt-PT', {
        day: '2-digit', month: '2-digit', year: 'numeric',
        hour: '2-digit', minute: '2-digit'
    });
}
function deletarEvento(id) {
    if (!confirm('Tem certeza que deseja excluir este evento?')) return;

    fetch(`${API_URL}/deletarevento/${id}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                showToast('Evento excluído com sucesso!');
                carregarEventosDoServidor(); // Atualiza a lista automaticamente
            } else {
                alert('Erro ao excluir o evento no servidor.');
            }
        })
        .catch(error => {
            console.error('Erro ao deletar:', error);
            alert('Erro de conexão ao tentar deletar.');
        });
}