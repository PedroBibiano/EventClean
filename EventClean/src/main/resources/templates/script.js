// Array em memória para armazenar os eventos (simulando um banco de dados)
let eventos = [];

// Funções para manipular a interface de Abas
function changeTab(tabName) {
    const tabCreate = document.getElementById('tab-create');
    const tabList = document.getElementById('tab-list');

    const contentCreate = document.getElementById('content-create');
    const contentList = document.getElementById('content-list');

    const activeClasses = 'border-indigo-500 text-indigo-600'.split(' ');
    const inactiveClasses = 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'.split(' ');

    if (tabName === 'create') {
        // Estilo dos botões
        tabCreate.classList.remove(...inactiveClasses);
        tabCreate.classList.add(...activeClasses);
        tabList.classList.remove(...activeClasses);
        tabList.classList.add(...inactiveClasses);

        // Lógica CSS: Adiciona 'active' na criação, remove da lista
        contentCreate.classList.add('active');
        contentList.classList.remove('active');
    } else {
        // Estilo dos botões
        tabList.classList.remove(...inactiveClasses);
        tabList.classList.add(...activeClasses);
        tabCreate.classList.remove(...activeClasses);
        tabCreate.classList.add(...inactiveClasses);

        // Lógica CSS: Adiciona 'active' na lista, remove da criação
        contentList.classList.add('active');
        contentCreate.classList.remove('active');

        renderEvents();
    }
}

// Função para mostrar notificação visual (Toast)
function showToast(message) {
    const toast = document.getElementById('toast');
    document.getElementById('toast-message').textContent = message;
    toast.classList.add('show');
    setTimeout(() => {
        toast.classList.remove('show');
    }, 3000);
}

// Formatar Data e Hora para o padrão
function formatarData(dataString) {
    const data = new Date(dataString);
    return data.toLocaleString('pt-PT', {
        day: '2-digit', month: '2-digit', year: 'numeric',
        hour: '2-digit', minute: '2-digit'
    });
}

// Traduzir as tags do Enum para visualização amigável e definir cores
function getBadgeStyle(tipo) {
    switch(tipo) {
        case 'WORKSHOP': return 'bg-blue-100 text-blue-800 border-blue-200';
        case 'PALESTRA': return 'bg-purple-100 text-purple-800 border-purple-200';
        case 'MUSICA': return 'bg-pink-100 text-pink-800 border-pink-200';
        case 'SEMINARIO': return 'bg-amber-100 text-amber-800 border-amber-200';
        default: return 'bg-gray-100 text-gray-800 border-gray-200';
    }
}

// Lidar com o envio do formulário
document.getElementById('eventoForm').addEventListener('submit', function(e) {
    e.preventDefault(); // Evita o recarregamento da página

    const novoEvento = {
        id: Date.now(),
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

    // Validar datas
    if (new Date(novoEvento.dataFim) <= new Date(novoEvento.dataInicio)) {
        showToast('Erro: Data de término deve ser posterior à data de início.');
        document.getElementById('toast').style.backgroundColor = '#EF4444'; // Red
        return;
    }

    eventos.push(novoEvento);

    // Resetar formulário
    this.reset();
    document.getElementById('toast').style.backgroundColor = '#10B981'; // Green
    showToast('Evento criado com sucesso!');

    // Atualizar contador
    const countBadge = document.getElementById('event-count');
    countBadge.textContent = eventos.length;
    countBadge.classList.remove('hidden');

    changeTab('list');
});

// Função para deletar um evento
function deleteEvento(id) {
    eventos = eventos.filter(evento => evento.id !== id);

    const countBadge = document.getElementById('event-count');
    if (eventos.length > 0) {
        countBadge.textContent = eventos.length;
    } else {
        countBadge.classList.add('hidden');
    }

    renderEvents();
    showToast('Evento removido!');
}

// Renderizar a lista de eventos
function renderEvents() {
    const grid = document.getElementById('events-grid');
    const emptyState = document.getElementById('empty-state');

    grid.innerHTML = '';

    if (eventos.length === 0) {
        grid.classList.add('hidden');
        emptyState.classList.remove('hidden');
        return;
    }

    emptyState.classList.add('hidden');
    grid.classList.remove('hidden');

    eventos.forEach(evento => {
        const card = document.createElement('div');
        card.className = 'bg-white rounded-xl shadow-sm hover:shadow-md transition-shadow border border-gray-100 overflow-hidden flex flex-col h-full';

        const badgeStyle = getBadgeStyle(evento.tipo);

        card.innerHTML = `
            <div class="p-6 flex-grow">
                <div class="flex justify-between items-start mb-4">
                    <span class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium border ${badgeStyle}">
                        ${evento.tipo}
                    </span>
                    <span class="text-xs text-gray-500 font-mono bg-gray-100 px-2 py-1 rounded">ID: ${evento.identificador}</span>
                </div>
                <h3 class="text-xl font-bold text-gray-900 mb-2">${evento.nome}</h3>
                <p class="text-gray-600 text-sm mb-4 line-clamp-3">${evento.descricao}</p>
                
                <div class="space-y-2 mt-auto">
                    <div class="flex items-center text-sm text-gray-500">
                        <svg class="mr-2 h-4 w-4 text-gray-400" xmlns="[http://www.w3.org/2000/svg](http://www.w3.org/2000/svg)" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
                        </svg>
                        <span>${formatarData(evento.dataInicio)} <br><span class="text-xs ml-6">até ${formatarData(evento.dataFim)}</span></span>
                    </div>
                    <div class="flex items-center text-sm text-gray-500">
                        <svg class="mr-2 h-4 w-4 text-gray-400" xmlns="[http://www.w3.org/2000/svg](http://www.w3.org/2000/svg)" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                        </svg>
                        ${evento.localEvento}
                    </div>
                    <div class="flex items-center text-sm text-gray-500">
                        <svg class="mr-2 h-4 w-4 text-gray-400" xmlns="[http://www.w3.org/2000/svg](http://www.w3.org/2000/svg)" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                        </svg>
                        ${evento.organizador}
                    </div>
                    <div class="flex items-center text-sm text-gray-500">
                        <svg class="mr-2 h-4 w-4 text-gray-400" xmlns="[http://www.w3.org/2000/svg](http://www.w3.org/2000/svg)" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z" />
                        </svg>
                        Capacidade: ${evento.capacidade} pessoas
                    </div>
                </div>
            </div>
            <div class="bg-gray-50 px-6 py-3 border-t border-gray-100 text-right">
                <button onclick="deleteEvento(${evento.id})" class="text-sm font-medium text-red-600 hover:text-red-800 transition-colors">
                    Remover Evento
                </button>
            </div>
        `;
        grid.appendChild(card);
    });
}
